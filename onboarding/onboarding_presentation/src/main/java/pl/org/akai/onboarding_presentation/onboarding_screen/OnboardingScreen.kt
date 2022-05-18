package pl.org.akai.onboarding_presentation.onboarding_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.poznan.put.michalxpz.core_ui.util.LocalSpacing
import com.poznan.put.michalxpz.core_ui.util.UiText
import pl.org.akai.onboarding_presentation.R

@Composable
fun OnboardingScreen(
    onButtonClick: () -> Unit,
    imageId: Int = R.drawable.ic_chess_svgrepo_com,
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

//            TextField(value = , onValueChange = )

            Button(onClick = onButtonClick) {
                Text(
                    text = UiText.DynamicString("Welcome!\nPlease tell us your name").value,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}