package pl.org.akai.onboarding_presentation.onboarding_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.poznan.put.michalxpz.core_ui.util.LocalSpacing
import com.poznan.put.michalxpz.core_ui.util.UiText
import com.poznan.put.michalxpz.core.R

@Composable
fun OnboardingScreen(
    onButtonClick: () -> Unit,
    imageId: Int = R.drawable.ic_chess_svgrepo_com,
//    viewModel: OnboardingViewModel
) {
    val spacing = LocalSpacing.current
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(spacing.large)
    ) {
        Column() {

            Image(
                painter = painterResource(id = imageId),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )

            Text(
                text = UiText.StringResource(R.string.onboarding_message).asString(),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing.mediumLarge)
                    .align(Alignment.CenterHorizontally)
            )

            TextField(value = "", onValueChange = {})

            Button(
                onClick = onButtonClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing.mediumLarge)
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(spacing.extraSmall)
                    .background(MaterialTheme.colorScheme.tertiary)
            ) {
                Text(
                    text = UiText.StringResource(R.string.Continue).asString(),
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}