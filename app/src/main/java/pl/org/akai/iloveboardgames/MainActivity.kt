package pl.org.akai.iloveboardgames

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import data.preferences.Preferences
import pl.org.akai.iloveboardgames.navigation.Routes
import pl.org.akai.game_list_presentation.game_list_screen.GamesScreen
import pl.org.akai.iloveboardgames.ui.theme.ILoveBoardGamesTheme
import pl.org.akai.onboarding_presentation.onboarding_screen.OnboardingScreen
import pl.org.akai.profile_screen_presentation.profile_screen.ProfileScreen
import pl.org.akai.ranking_history_presentation.ranking_history_screen.RankingHistoryScreen
import pl.org.akai.synchronization_presentation.synchronization_screen.SynchronizationScreen
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val shouldShowOnboarding = preferences.loadShouldShowOnboarding()
        setContent {
            ILoveBoardGamesTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    val startDestination = if (shouldShowOnboarding) Routes.ONBOARDING else Routes.GAME_SCREEN
                    val scope = rememberCoroutineScope()
                    NavHost(navController = navController, startDestination = startDestination) {
                        composable(Routes.ONBOARDING) {
                            OnboardingScreen(
                                onButtonClick = {
                                    navController.navigate(Routes.GAME_SCREEN)
                                }
                            )
                        }

                        composable(Routes.PROFILE) {
                            ProfileScreen()
                        }

                        composable(Routes.GAME_SCREEN) {
                            GamesScreen()

                        }

                        composable(
                            route ="${Routes.RANKING_HISTORY}/{gameId}",
                            arguments = listOf(navArgument("gameId") { type = NavType.StringType })
                        ) { backStackEntry ->
                            RankingHistoryScreen(backStackEntry.arguments?.getString("gameId"))
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