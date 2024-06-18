package com.helicoptera.dotarunetimer.temp.game.ui

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FastForward
import androidx.compose.material.icons.filled.FastRewind
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.helicoptera.dotarunetimer.data.GameType
import com.helicoptera.dotarunetimer.data.MapActivity

private const val CONTROLS_SIZE = 40

@Composable
fun GameScreen(
    gameType: GameType,
    gameViewModel: GameViewModel = hiltViewModel<GameViewModel, GameViewModel.ViewModelFactory> { factory ->
        factory.create(isTurbo = gameType == GameType.TURBO)
    }
) {
    val timerText by gameViewModel.timerText.collectAsStateWithLifecycle()
    val timerState by gameViewModel.timerState.collectAsStateWithLifecycle()
    val mapActivity by gameViewModel.mapActivity.collectAsStateWithLifecycle()
    Box(modifier = Modifier.fillMaxSize()) {
        Timer(
            timerText = timerText,
            timerState = timerState,
            mapActivity = mapActivity,
            onAction = gameViewModel::onAction
        )
    }
    Text(gameType.name)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Timer(
    timerText: String,
    timerState: TimerState,
    mapActivity: MapActivity?,
    onAction: (GameAction) -> Unit
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (timer, rewind, forward, control, notificationBox) = createRefs()
        val centerGuideline = createGuidelineFromStart(0.5F)
        Text(text = timerText,
            fontSize = 36.sp,
            modifier = Modifier.constrainAs(timer) {
                linkTo(parent.top, parent.bottom, bias = 0.35F)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Button(
            onClick = { onAction(GameAction.REWIND) },
            modifier = Modifier
                .padding(top = 8.dp)
                .size(CONTROLS_SIZE.dp)
                .constrainAs(rewind) {
                    linkTo(parent.start, centerGuideline, bias = 0.4F)
                    top.linkTo(timer.bottom)
                }) {
            Icon(
                imageVector = Icons.Filled.FastRewind,
                contentDescription = "pause",
                modifier = Modifier
                    .size(24.dp)
                    .wrapContentSize(align = Alignment.Center),
            )
        }
        Button(
            onClick = { onAction(GameAction.FORWARD) },
            modifier = Modifier
                .padding(top = 8.dp)
                .size(CONTROLS_SIZE.dp)
                .constrainAs(forward) {
                    linkTo(centerGuideline, parent.end, bias = 0.6F)
                    top.linkTo(timer.bottom)
                }) {
            Icon(
                imageVector = Icons.Filled.FastForward,
                contentDescription = "pause",
                modifier = Modifier
                    .size(24.dp)
                    .wrapContentSize(align = Alignment.Center),
            )
        }
        Button(
            onClick = {
                onAction(GameAction.RESUME_PAUSE)
            },
            modifier = Modifier
                .size(CONTROLS_SIZE.dp)
                .constrainAs(control) {
                    linkTo(rewind.end, forward.start, bias = 0.5F)
                    top.linkTo(forward.bottom)
                }) {
            Icon(
                if (timerState == TimerState.RUNNING) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                contentDescription = "pause",
                modifier = Modifier
                    .size(40.dp)
                    .wrapContentSize(align = Alignment.Center),
            )
        }

        val padding by animateDpAsState(
            targetValue = if (mapActivity != null) 0.dp else -40.dp)
        Box(
            modifier = Modifier
                .offset(y = padding)
                .padding(top = 4.dp)
                .fillMaxWidth()
                .height(40.dp)
                .constrainAs(notificationBox) {
                    linkTo(parent.start, parent.end)
                    top.linkTo(parent.top)
                }
                .clickable {

                },
            contentAlignment = Alignment.Center
        ) {
            if (mapActivity != null) {
                Text(
                    fontSize = 12.sp,
                    text = mapActivity.toString()
                )
            }
        }
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun TimerPreviewWithHours() {
    Timer(
        timerText = "40:01",
        timerState = TimerState.RUNNING,
        mapActivity = MapActivity.LOTUS,
        onAction =  {})
}