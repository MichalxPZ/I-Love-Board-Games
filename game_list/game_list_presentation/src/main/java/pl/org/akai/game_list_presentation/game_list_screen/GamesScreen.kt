package pl.org.akai.game_list_presentation.game_list_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.poznan.put.michalxpz.core_ui.util.LocalSpacing
import com.poznan.put.michalxpz.core_ui.util.UiText
import pl.org.akai.game_list_presentation.game_list_screen.components.GameItemEntry
import com.poznan.put.michalxpz.core.R
import com.poznan.put.michalxpz.core_ui.util.UiEvent
import navigation.Routes
import pl.org.akai.game_list_domain.model.GameModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
@ExperimentalComposeUiApi
fun GamesScreen(
    modifier: Modifier = Modifier,
    viewModel: GameListScreenViewModel = hiltViewModel(),
    navigateToRoute: (String) -> Unit
) {
    val state = viewModel.state
    val spacing = LocalSpacing.current
    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel) {
        viewModel.uiEvent.collect { uiEvent ->
            when(uiEvent) {
                is UiEvent.Navigate -> {
                    navigateToRoute(uiEvent.route)
                }
                is UiEvent.ShowErrorSnackbar -> {
                    Toast.makeText(context, uiEvent.messageError, Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    Column(
        modifier = modifier
            .padding(spacing.mediumSmall),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(
            value = state.textFieldValue,
            onValueChange = { value ->
                viewModel.onEvent(GameListScreenEvent.OnTextFieldValueChange(value))
            },
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Search...")
            },
            maxLines = 1,
            singleLine = true
        )
        Spacer(modifier = Modifier.height(spacing.mediumSmall))

        AnimatedVisibility(visible = !state.isLoading) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                LazyColumn(
                    modifier = Modifier
                        .height(270.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .border(
                            BorderStroke(4.dp, MaterialTheme.colorScheme.secondary),
                            RoundedCornerShape(18.dp)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    stickyHeader {
                        Text(
                            text = UiText.StringResource(R.string.Your_Games).asString(),
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(spacing.small)
                        )
                    }
                    items(state.games) { game ->
                        GameItemEntry(
                            gameModel = game,
                            modifier = Modifier.clickable  {
                                viewModel.onEvent(GameListScreenEvent.OnGameClicked(
                                    route = "${Routes.RANKING_HISTORY}/${game.id}"
                                ))
                            }
                        )
                        Divider(modifier = Modifier,
                            color = MaterialTheme.colorScheme.background,
                            thickness = 4.dp)
                        Divider(modifier = Modifier,
                            color = MaterialTheme.colorScheme.onPrimary,
                            thickness = 1.dp)
                        Divider(modifier = Modifier,
                            color = MaterialTheme.colorScheme.background,
                            thickness = 4.dp)
                    }
                }

                Spacer(modifier = Modifier.height(spacing.small))
                Spacer(modifier = Modifier
                    .height(1.dp)
                    .background(MaterialTheme.colorScheme.tertiary))
                Spacer(modifier = Modifier.height(spacing.small))

                LazyColumn(
                    modifier = Modifier
                        .height(270.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .border(
                            BorderStroke(4.dp, MaterialTheme.colorScheme.secondary),
                            RoundedCornerShape(18.dp)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    stickyHeader {
                        Text(
                            text = UiText.StringResource(R.string.Your_Extensions).asString(),
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(spacing.small)
                        )
                    }
                    items(state.extensions) { extension ->
                        GameItemEntry(
                            gameModel = extension,
                            modifier = Modifier.clickable {
                                viewModel.onEvent(GameListScreenEvent.OnGameClicked(
                                    route = "${Routes.RANKING_HISTORY}/${extension.id}"
                                ))
                            }
                        )
                        Divider(modifier = Modifier,
                            color = MaterialTheme.colorScheme.background,
                            thickness = 4.dp)
                        Divider(modifier = Modifier,
                            color = MaterialTheme.colorScheme.onPrimary,
                            thickness = 1.dp)
                        Divider(modifier = Modifier,
                            color = MaterialTheme.colorScheme.background,
                            thickness = 4.dp)
                    }
                }
            }
        }

        AnimatedVisibility(visible = state.isLoading) {
            CircularProgressIndicator()
        }
    }
}