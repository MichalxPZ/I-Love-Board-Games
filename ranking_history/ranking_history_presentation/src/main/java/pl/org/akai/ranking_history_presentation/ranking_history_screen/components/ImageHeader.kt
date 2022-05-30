package pl.org.akai.ranking_history_presentation.ranking_history_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.poznan.put.michalxpz.core_ui.util.LocalSpacing
import pl.org.akai.ranking_history_presentation.R


//via https://github.com/jipariz/ComposeMotion

@OptIn(ExperimentalMotionApi::class)
@Composable
fun ImageHeader(
    progress: Float,
    imageUrl: String,
    title: String,
    scrollableBody: @Composable () -> Unit
) {

    val spacing = LocalSpacing.current

    MotionLayout(
        start = startConstraintSet(),
        end = endConstraintSet(),
            progress = progress,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = "image",
            modifier = Modifier
                .layoutId("poster")
                .background(MaterialTheme.colorScheme.primary),
            contentScale = ContentScale.FillWidth,
            placeholder = painterResource(id = com.poznan.put.michalxpz.core.R.drawable.ic_chess_svgrepo_com),
            alpha = 1f - progress // Update alpha based on progress. Expanded -> 1f / Collapsed -> 0f (transparent)
        )
        Text(
            text = title,
            modifier = Modifier
                .layoutId("title")
                .wrapContentHeight(),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
        Box(
            Modifier
                .layoutId("content")
        ) {
            scrollableBody()
        }
    }
}