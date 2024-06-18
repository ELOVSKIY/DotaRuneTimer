package com.helicoptera.dotarunetimer.temp.settings.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Switch
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.ToggleChip
import androidx.wear.compose.material.ToggleChipDefaults
import androidx.wear.tooling.preview.devices.WearDevices
import com.helicoptera.dotarunetimer.R
import com.helicoptera.dotarunetimer.ui.theme.DotaRuneTimerTheme

@Composable
fun SettingsToggle(
    modifier: Modifier = Modifier,
    checked: Boolean,
    title: String,

    onCheckChange: (Boolean) -> Unit
) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // When we have both label and secondary label present limit both to 1 line of text
        ToggleChip(
            label = {
                Text(title)
            },

            checked = checked,
            // For Switch  toggle controls the Wear Material UX guidance is to set the
            // unselected toggle control color to ToggleChipDefaults.switchUncheckedIconColor()
            // rather than the default.
            colors = ToggleChipDefaults.toggleChipColors(
                uncheckedToggleControlColor = ToggleChipDefaults.SwitchUncheckedIconColor
            ),
            toggleControl = {
                Switch(
                    checked = checked,
                    enabled = true,
                    modifier = Modifier.semantics {
                        this.contentDescription =
                            if (checked) "On" else "Off"
                    }
                )
            },
            onCheckedChange = onCheckChange,
            appIcon = {
                Icon(
                    Icons.Filled.Call,
                    contentDescription = "call",
                    modifier = Modifier
                        .size(24.dp)
                        .wrapContentSize(align = Alignment.Center),
                )
            },
            enabled = true,
        )
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun SettingsTogglePreview() {
    DotaRuneTimerTheme {
//        SettingsToggle(checked = false) {}
    }
}