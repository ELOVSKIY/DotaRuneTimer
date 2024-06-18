package com.helicoptera.dotarunetimer.ui.screen.type

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import com.helicoptera.dotarunetimer.data.GameType
import com.helicoptera.dotarunetimer.ui.screen.type.TypeScreenConstants.BUTTONS_HEIGHT
import com.helicoptera.dotarunetimer.ui.theme.DotaRuneTimerTheme

@Composable
fun GameTypeScreen(onButtonClick: (GameType) -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp)) {
        Button(
            onClick = {onButtonClick(GameType.ALL_PICK) },
            modifier = Modifier.fillMaxWidth()
                .height(BUTTONS_HEIGHT),
        ) {
            Text("All Pick")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {onButtonClick(GameType.TURBO) },
            modifier = Modifier.fillMaxWidth()
                .height(BUTTONS_HEIGHT),
        ) {
            Text("Turbo")
        }
    }
}

private object TypeScreenConstants {
    val BUTTONS_HEIGHT = 28.dp
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun StartScreenPreview() {
    DotaRuneTimerTheme {
        GameTypeScreen({})
    }
}