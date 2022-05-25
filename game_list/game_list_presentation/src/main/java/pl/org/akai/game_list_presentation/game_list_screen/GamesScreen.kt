package pl.org.akai.game_list_presentation.game_list_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.poznan.put.michalxpz.core_ui.util.LocalSpacing
import com.poznan.put.michalxpz.core_ui.util.UiText
import pl.org.akai.game_list_presentation.game_list_screen.components.GameItemEntry
import com.poznan.put.michalxpz.core.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
@ExperimentalComposeUiApi
fun GamesScreen(
    modifier: Modifier = Modifier,
    viewModel: GameListScreenViewModel = hiltViewModel(),
) {
    val state = viewModel.state
    val spacing = LocalSpacing.current

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
                            RoundedCornerShape(15.dp)
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
                        GameItemEntry(gameModel = game)

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
                Spacer(modifier = Modifier.height(1.dp).background(MaterialTheme.colorScheme.tertiary))
                Spacer(modifier = Modifier.height(spacing.small))

                LazyColumn(
                    modifier = Modifier
                        .height(270.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .border(
                            BorderStroke(4.dp, MaterialTheme.colorScheme.secondary),
                            RoundedCornerShape(15.dp)
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
                        GameItemEntry(gameModel = extension)
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