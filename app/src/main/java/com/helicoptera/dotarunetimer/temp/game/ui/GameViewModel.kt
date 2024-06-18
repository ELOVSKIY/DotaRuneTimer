package com.helicoptera.dotarunetimer.temp.game.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.helicoptera.dotarunetimer.data.MapActivity
import com.helicoptera.dotarunetimer.temp.game.domain.ProviderEventFlowUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transformLatest
import kotlinx.coroutines.flow.update
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

@HiltViewModel(assistedFactory = GameViewModel.ViewModelFactory::class)
class GameViewModel @AssistedInject constructor(
    @Assisted private val isTurbo: Boolean,
    private val providerEventFlowUseCase: ProviderEventFlowUseCase
) : ViewModel() {

    private val _timerState = MutableStateFlow(TimerState.PAUSED)
    val timerState = _timerState.asStateFlow()

    private val _time = MutableStateFlow(0L)

    private val formatter = DateTimeFormatter.ofPattern("H:mm:ss")

    private val _mapActivity = MutableStateFlow<MapActivity?>(null)
    val mapActivity = _mapActivity.asStateFlow()

    val timerText = _time
        .map { millis ->
            LocalTime.ofNanoOfDay(millis * 1_000_000).format(formatter)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            "0:00:00"
        )

    init {
        _timerState.flatMapLatest { state ->
            if (state == TimerState.RUNNING) {
                getTimerFlow()
            } else {
                emptyFlow()
            }
        }.onEach { timeDiffMillis ->
            //TODO
            _time.update { it + timeDiffMillis * 10 }
        }.launchIn(viewModelScope)

        _mapActivity.transformLatest<MapActivity?, Unit> { mapActivity ->
            if (mapActivity != null) {
                delay(5000L)
                _mapActivity.update { null }
            }
        }.launchIn(viewModelScope)

        providerEventFlowUseCase.provideEventFlow(
            _time,
            isTurbo
        ).onEach {

        }.launchIn(viewModelScope)
    }

    fun onAction(gameAction: GameAction) {
        when (gameAction) {
            GameAction.RESUME_PAUSE -> {
                onResumePauseClicked()
            }

            GameAction.REWIND -> {
                _time.update {
                    (it - TimeUnit.SECONDS.toMillis(5L)).coerceAtLeast(
                        0L
                    )
                }
            }

            GameAction.FORWARD -> {
                _time.update {
                    it + TimeUnit.SECONDS.toMillis(5L)
                }
            }

            GameAction.ON_NOTIFICATION_CLICK -> {
                _mapActivity.update { null }
            }
        }
    }

    private fun onResumePauseClicked() {
        _timerState.update { state ->
            when (state) {
                TimerState.PAUSED -> TimerState.RUNNING
                TimerState.RUNNING -> TimerState.PAUSED
            }
        }
    }

    private fun getTimerFlow(): Flow<Long> = flow {
        var startMillis = System.currentTimeMillis()
        while (true) {
            val currentMillis = System.currentTimeMillis()
            val timeDiff = if (currentMillis > startMillis) {
                currentMillis - startMillis
            } else 0L
            emit(timeDiff)
            startMillis = System.currentTimeMillis()
            delay(TIME_FETCH_DELAY_MILLIS)
        }
    }

    @AssistedFactory
    interface ViewModelFactory {
        fun create(isTurbo: Boolean): GameViewModel
    }

    companion object {
        private const val TIME_FETCH_DELAY_MILLIS = 10L
    }
}