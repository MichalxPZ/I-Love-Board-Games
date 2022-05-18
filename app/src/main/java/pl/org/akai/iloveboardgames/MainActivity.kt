package pl.org.akai.iloveboardgames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import navigation.Routes
import pl.org.akai.game_list_presentation.game_list_screen.GamesScreen
import pl.org.akai.iloveboardgames.ui.theme.ILoveBoardGamesTheme
import pl.org.akai.onboarding_presentation.onboarding_screen.OnboardingScreen
import pl.org.akai.profile_screen_presentation.profile_screen.ProfileScreen
import pl.org.akai.ranking_history_presentation.ranking_history_screen.RankingHistoryScreen
import pl.org.akai.synchronization_presentation.synchronization_screen.SynchronizationScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ILoveBoardGamesTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    val startDestination = Routes.ONBOARDING
                    NavHost(navController = navController, startDestination = startDestination) {
                        composable(Routes.ONBOARDING) {
                            OnboardingScreen()
                        }

                        composable(Routes.PROFILE) {
                            ProfileScreen()
                        }

                        composable(Routes.GAME_SCREEN) {
                            GamesScreen()
                        }

                        composable(Routes.RANKING_HISTORY) {
                            RankingHistoryScreen()
                        }

                        composable(Routes.SYNCHORNIZATION) {
                            SynchronizationScreen()
                        }

                    }
                }
            }
        }
    }
}