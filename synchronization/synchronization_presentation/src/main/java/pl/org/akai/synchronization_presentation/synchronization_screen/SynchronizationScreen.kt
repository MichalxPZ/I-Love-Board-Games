package pl.org.akai.synchronization_presentation.synchronization_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.poznan.put.michalxpz.core.R
import com.poznan.put.michalxpz.core_ui.util.LocalSpacing
import com.poznan.put.michalxpz.core_ui.util.UiEvent
import com.poznan.put.michalxpz.core_ui.util.UiText
import com.poznan.put.michalxpz.core_ui.util.dialogs.Dialog
import utils.DialogType

@Composable
fun SynchronizationScreen(
    navigateToRoute: (String) -> Unit,
    viewModel: SynchronizationScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state
    val spacing = LocalSpacing.current

    LaunchedEffect(key1 = viewModel) {
        viewModel.uiEvent.collect { uiEvent ->
            when(uiEvent) {
                is UiEvent.Navigate -> {
                    navigateToRoute(uiEvent.route)
                }
                is UiEvent.ShowErrorDialog -> {
                    viewModel.onEvent(SynchronizationScreenEvent.OnShowDialog(uiEvent.messageError, DialogType.ERROR))
                }
                is UiEvent.ShowInfoDialog -> {
                    viewModel.onEvent(SynchronizationScreenEvent.OnShowDialog(uiEvent.messageError, DialogType.INFO))
                }
                is UiEvent.ShowSuccessDialog -> {
                    viewModel.onEvent(SynchronizationScreenEvent.OnShowDialog(uiEvent.messageError, DialogType.SUCCESS))
                }
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(spacing.large),
        contentAlignment = Alignment.Center
    ) {

        AnimatedVisibility(visible = state.showDialog) {
            Dialog(
                title =state.dialogTitle,
                desc =state.dialogDesc,
                onDismiss = { viewModel.onEvent(SynchronizationScreenEvent.OnDialogDismiss) },
                onAccept = { viewModel.onEvent(SynchronizationScreenEvent.OnSynchAccepted(type = state.dialogType)) },
                resId = state.dialogImgId
            )
        }

        AnimatedVisibility(visible = !state.isSynchronizing && !state.showDialog) {
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

                Spacer(modifier = Modifier.height(spacing.extraLarge))

                Button(
                    onClick = {
                        viewModel.onEvent(SynchronizationScreenEvent.OnClearDataClicked)
                    },
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        disabledContainerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f)
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 24.dp,
                        pressedElevation = 0.dp,
                        hoveredElevation = 12.dp
                    )
                ) {
                    Text(
                        text = UiText.StringResource(R.string.Clear_data).asString(),
                        style = MaterialTheme.typography.headlineSmall,
                    )
                }
            }
        }

        AnimatedVisibility(visible = state.isSynchronizing && !state.showDialog) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
        }
    }


}