package pl.org.akai.game_list_presentation.game_list_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import pl.org.akai.game_list_domain.model.GameModel
import com.poznan.put.michalxpz.core.R
import com.poznan.put.michalxpz.core_ui.util.LocalSpacing
import pl.org.akai.game_list_domain.model.GameType

@Composable
fun GameItemEntry(
    gameModel: GameModel
) {

    val spacing = LocalSpacing.current

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth().padding(spacing.extraSmall)
    ) {
        
        Text(
            text = gameModel.id.toString(),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.align(Alignment.CenterVertically).weight(0.4f)
        )

        Spacer(modifier = Modifier.width(spacing.mediumSmall))

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(gameModel.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = "image",
            placeholder = painterResource(id = R.drawable.ic_chess_svgrepo_com),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .height(100.dp)
                .aspectRatio(1f)
                .align(Alignment.CenterVertically)
        )

        Spacer(modifier = Modifier.width(spacing.large))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = gameModel.title,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Blue,
                maxLines = 1,
            )

            Spacer(modifier = Modifier.height(spacing.extraSmall))

            Text(
                text = gameModel.year.toString(),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(spacing.small))

            if (gameModel.type == GameType.GAME) {
                Text(
                    text = "Ranking: ${gameModel.rankingLatest}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.weight(1f)
                )
            }
        }

    }
}