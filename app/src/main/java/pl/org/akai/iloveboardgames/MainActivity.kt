package pl.org.akai.iloveboardgames

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import data.local.GamesDao
import data.preferences.Preferences
import kotlinx.coroutines.launch
import navigation.Routes
import pl.org.akai.game_list_presentation.game_list_screen.GamesScreen
import pl.org.akai.iloveboardgames.components.bottom_bar.BgTabRow
import pl.org.akai.iloveboardgames.ui.theme.ILoveBoardGamesTheme
import pl.org.akai.onboarding_presentation.onboarding_screen.OnboardingScreen
import pl.org.akai.profile_screen_presentation.profile_screen.ProfileScreen
import pl.org.akai.ranking_history_presentation.ranking_history_screen.RankingHistoryScreen
import pl.org.akai.synchronization_presentation.synchronization_screen.SynchronizationScreen
import javax.inject.Inject


@OptIn(ExperimentalComposeUiApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val shouldShowOnboarding = preferences.loadShouldShowOnboarding()
        setContent {
            ILoveBoardGamesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val startDestination = if (shouldShowOnboarding) Routes.ONBOARDING else Routes.PROFILE
                    val scope = rememberCoroutineScope()
                    val backstackEntry = navController.currentBackStackEntryAsState()

                    Column(
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        NavHost(navController = navController, startDestination = startDestination, modifier = Modifier) {
                            composable(Routes.ONBOARDING) {
                                OnboardingScreen(
                                    onButtonClick = {
                                        navController.navigate(Routes.PROFILE)
                                    }
                                )
                            }

                            composable(Routes.PROFILE) {
                                ProfileScreen(
                                    onProfileClick = { navController.navigate(Routes.ONBOARDING) }
                                )
                            }

                            composable(Routes.GAME_SCREEN) {
                                fun navigateToRoute(route: String) = navController.navigate(route)
                                GamesScreen(
                                    navigateToRoute = { navigateToRoute(it) }
                                )
                            }

                            composable(
                                route ="${Routes.RANKING_HISTORY}/{gameId}",
                                arguments = listOf(
                                    navArgument("gameId") { type = NavType.StringType },
                                    )
                            ) { backStackEntry ->
                                RankingHistoryScreen(
                                    gameId = backStackEntry.arguments?.getString("gameId")?.toInt() ?: 0,
                                    onBackArrowPressed = { navController.popBackStack() }
                                )
                            }

                            composable(Routes.SYNCHORNIZATION) {
                                SynchronizationScreen()
                            }

                        }

                    NavBar(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun NavBar(navController: NavController) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BgTabRow(
        allRoutes = listOf(Routes.SYNCHORNIZATION, Routes.PROFILE, Routes.GAME_SCREEN),
        onTabSelected = { screen ->
            navController.navigate(screen)
        },
        currentRoute = backStackEntry.value?.destination?.route ?: Routes.PROFILE,
        modifier = Modifier
    )
}
