package com.helicoptera.dotarunetimer.temp.game.ui

sealed interface GameAction {
    data object RESUME_PAUSE: GameAction
    data object REWIND: GameAction
    data object FORWARD: GameAction
    data object ON_NOTIFICATION_CLICK: GameAction
}