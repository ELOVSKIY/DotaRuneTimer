package com.helicoptera.dotarunetimer.data

class MapActivityKeyProvider {

    fun provideEnabledKey(mapActivity: MapActivity): String {
        return when (mapActivity) {
            MapActivity.LOTUS -> "LOTUS_ENABLED"
            MapActivity.BOUNTY_RUNE -> "BOUNTY_RUNE_ENABLED"
            MapActivity.WATER_RUNE -> "WATER_RUNE_ENABLED"
            MapActivity.POWER_RUNE -> "POWER_RUNE_ENABLED"
            MapActivity.WISDOM_RUNE -> "WISDOM_RUNE_ENABLED"
        }
    }
}