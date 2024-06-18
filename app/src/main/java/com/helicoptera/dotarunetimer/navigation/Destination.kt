package com.helicoptera.dotarunetimer.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {

    @Serializable
    data class GameScreen(
        val isTurbo: Boolean
    )
}