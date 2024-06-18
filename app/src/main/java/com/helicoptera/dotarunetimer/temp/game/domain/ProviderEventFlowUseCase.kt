package com.helicoptera.dotarunetimer.temp.game.domain

import com.helicoptera.dotarunetimer.data.MapActivity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ProviderEventFlowUseCase @Inject constructor(){

    fun provideEventFlow(timeFlow: Flow<Long>, isTurbo: Boolean): Flow<MapActivity> {
        val lotusDelayInSec = if (isTurbo) 210L else 420L
        return timeFlow.mapNotNull { it - TimeUnit.SECONDS.toMillis(10) }.mapNotNull { timeInMillis ->
            if (timeInMillis != 0L &&
                timeInMillis - TimeUnit.MINUTES.toMillis(3) <= ACTIVATE_BOUNDS) {
                return@mapNotNull MapActivity.BOUNTY_RUNE
            }
            if (timeInMillis >= TimeUnit.MINUTES.toMillis(6) &&
                timeInMillis % TimeUnit.MINUTES.toMillis(2) <= ACTIVATE_BOUNDS) {
                return@mapNotNull MapActivity.BOUNTY_RUNE
            }
            if (timeInMillis >= TimeUnit.MINUTES.toMillis(2) &&
                timeInMillis <= TimeUnit.MINUTES.toMillis(4) &&
                timeInMillis % TimeUnit.MINUTES.toMillis(2) <= ACTIVATE_BOUNDS) {
                return@mapNotNull MapActivity.BOUNTY_RUNE
            }
            if (timeInMillis >= TimeUnit.MINUTES.toMillis(6) &&
                timeInMillis % TimeUnit.MINUTES.toMillis(2) <= ACTIVATE_BOUNDS) {
                return@mapNotNull MapActivity.LOTUS
            }
            if (timeInMillis >= TimeUnit.SECONDS.toMillis(lotusDelayInSec) &&
                timeInMillis % TimeUnit.SECONDS.toMillis(lotusDelayInSec) <= ACTIVATE_BOUNDS) {
                return@mapNotNull MapActivity.LOTUS
            }

            return@mapNotNull null
        }
    }

    companion object {
        //TODO
        private const val ACTIVATE_BOUNDS = 250 * 10L
    }
}