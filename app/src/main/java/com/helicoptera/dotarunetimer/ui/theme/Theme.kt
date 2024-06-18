package com.helicoptera.dotarunetimer.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun DotaRuneTimerTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    /**
     * Empty theme to customize for your app.
     * See: https://developer.android.com/jetpack/compose/designsystems/custom
     */
    val lightColors = lightColorScheme()
    val darkColors = darkColorScheme()

    val colors = if (useDarkTheme) {
        darkColors
    } else {
        lightColors
    }

    MaterialTheme(
        colorScheme  = colors,
        content = content,
    )
}