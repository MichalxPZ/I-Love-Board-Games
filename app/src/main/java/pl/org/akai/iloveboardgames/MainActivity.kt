package pl.org.akai.iloveboardgames

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import pl.org.akai.iloveboardgames.navigation.Routes
import pl.org.akai.game_list_presentation.game_list_screen.GamesScreen
import pl.org.akai.iloveboardgames.ui.theme.ILoveBoardGamesTheme
import data.preferences.ApplicationSettingSerializer
import data.preferences.ApplicationSettings
import pl.org.akai.onboarding_presentation.onboarding_screen.OnboardingScreen
import pl.org.akai.profile_screen_presentation.profile_screen.ProfileScreen
import pl.org.akai.ranking_history_presentation.ranking_history_screen.RankingHistoryScreen
import pl.org.akai.synchronization_presentation.synchronization_screen.SynchronizationScreen

val Context.dataStore: DataStore<ApplicationSettings> by dataStore(fileName = "settings", serializer = ApplicationSettingSerializer)

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContent {
            ILoveBoardGamesTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    val applicationSettings = dataStore.data.collectAsState(initial = ApplicationSettings()).value
                    val navController = rememberNavController()
                    val startDestination = if (applicationSettings.shouldShowOnboarding) Routes.ONBOARDING else Routes.PROFILE
                    val scope = rememberCoroutineScope()
                    NavHost(navController = navController, startDestination = startDestination) {
                        composable(Routes.ONBOARDING) {
                            OnboardingScreen(
                                onButtonClick = {}
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