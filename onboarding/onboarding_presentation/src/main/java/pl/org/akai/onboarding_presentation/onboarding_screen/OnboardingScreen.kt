package pl.org.akai.onboarding_presentation.onboarding_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.poznan.put.michalxpz.core_ui.util.LocalSpacing
import com.poznan.put.michalxpz.core_ui.util.UiText
import com.poznan.put.michalxpz.core.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OnboardingScreen(
    onButtonClick: () -> Unit,
    imageId: Int = R.drawable.ic_chess_svgrepo_com,
    viewModel: OnboardingViewModel = hiltViewModel(),
) {

    val state = viewModel.state
    val keyboardController = LocalSoftwareKeyboardController.current
    val spacing = LocalSpacing.current
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = spacing.large)
            .padding(bottom = spacing.large)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.mediumLarge),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {

            Image(
                painter = painterResource(id = imageId),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.height(spacing.large))

            Text(
                text = UiText.StringResource(R.string.onboarding_message).asString(),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(spacing.mediumLarge))

            OutlinedTextField(
                value = state.userName,
                onValueChange = { viewModel.onEvent(OnboardingScreenEvent.OnNameTextFieldValueChanged(it)) },

                singleLine = true,
                colors= TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary
                ),
                placeholder = {
                    Text(text = UiText.StringResource(R.string.whats_your_name).asString())
                },
                keyboardActions = KeyboardActions {
                    keyboardController?.hide()
                }
            )

            Spacer(modifier = Modifier.height(spacing.mediumLarge))

            Button(
                onClick = {
                    viewModel.onEvent(OnboardingScreenEvent.OnContinueClicked)
                    onButtonClick()
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
                    hoveredElevation = 12.dp,
                    disabledElevation = 0.dp
                ),
                enabled = state.buttonActive

            ) {
                Text(
                    text = UiText.StringResource(R.string.Continue).asString(),
                    style = MaterialTheme.typography.headlineSmall,
                )
            }
        }
    }
}