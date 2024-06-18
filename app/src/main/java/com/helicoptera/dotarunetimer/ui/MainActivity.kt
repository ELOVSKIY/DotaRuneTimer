/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.helicoptera.dotarunetimer.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.helicoptera.dotarunetimer.data.GameType
import com.helicoptera.dotarunetimer.temp.game.ui.GameScreen
import com.helicoptera.dotarunetimer.temp.settings.ui.SettingsScreen
import com.helicoptera.dotarunetimer.ui.screen.start.StartScreen
import com.helicoptera.dotarunetimer.ui.screen.type.GameTypeScreen
import com.helicoptera.dotarunetimer.ui.theme.DotaRuneTimerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            WearApp("Android")
        }
    }
}

@Composable
fun WearApp(greetingName: String) {
    DotaRuneTimerTheme {
        val navController = rememberSwipeDismissableNavController()
        NavHost(
            navController = navController,
            startDestination = "game_screen"
        ) {
            composable("start_screen") {
                StartScreen({navController.navigate("game_type_screen")}, {})
            }
            composable("game_type_screen") {
                GameTypeScreen { gameType ->
                    navController.navigate("game_screen/${gameType.name}")
                }
            }
            composable(
                "game_screen",
//                arguments = listOf(navArgument("type") { type = NavType.BoolType })
            ) { backStackEntry ->
//                val gameType = if (backStackEntry.arguments?.getBoolean("isTurbo") == true) GameType.TURBO else GameType.ALL_PICK
                GameScreen(GameType.TURBO)
//                GameScreen(GameType.valueOf(backStackEntry.arguments?.getString("userId") ?: "turbo"))
            }
            composable("settings_screen") {
                SettingsScreen()
            }
        }
    }
}


@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp("Preview Android")
}