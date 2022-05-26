package pl.org.akai.synchronization_presentation.synchronization_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.poznan.put.michalxpz.core.R
import com.poznan.put.michalxpz.core_ui.util.LocalSpacing
import com.poznan.put.michalxpz.core_ui.util.UiText

@Composable
fun SynchronizationScreen(
    viewModel: SynchronizationScreenViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val spacing = LocalSpacing.current

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(spacing.large),
        contentAlignment = Alignment.Center
    ) {

        AnimatedVisibility(visible = !state.isSynchronizing) {
            Column {

                Image(
                    imageVector = Icons.Outlined.Refresh,
                    contentDescription = "refresh",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clickable {
                            viewModel.onEvent(SynchronizationScreenEvent.OnSynchronizationButtonClick)
                        }
                )

                Spacer(modifier = Modifier.height(spacing.mediumSmall))

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = UiText.StringResource(R.string.Last_synchronized).asString() ,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                    )
                    Spacer(modifier = Modifier.height(spacing.extraSmall))

                    Text(
                        text = UiText.DynamicString(state.lastSychronized.toString()).asString() ,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }

        AnimatedVisibility(visible = state.isSynchronizing) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
        }
    }


}