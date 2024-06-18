package com.helicoptera.dotarunetimer.ui.screen.start

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.helicoptera.dotarunetimer.ui.theme.DotaRuneTimerTheme

@Composable
fun StartScreen(
    onPlayClicked: () -> Unit,
    onSettingsClicked: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier.size(64.dp),
            onClick = onPlayClicked,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
        ) {
            Icon(
                modifier = Modifier.size(48.dp),
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = "Play",
            )

        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            modifier = Modifier.size(36.dp),
            onClick = { },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
        ) {
            Icon(
                modifier = Modifier.size(28.dp),
                imageVector = Icons.Filled.Settings,
                contentDescription = "Settings",
            )

        }
    }
}


@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun StartScreenPreview() {
    DotaRuneTimerTheme {
        StartScreen({}, {})
    }
}