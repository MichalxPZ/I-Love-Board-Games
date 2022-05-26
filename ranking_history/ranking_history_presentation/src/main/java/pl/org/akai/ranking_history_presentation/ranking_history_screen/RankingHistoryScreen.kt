package pl.org.akai.ranking_history_presentation.ranking_history_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.poznan.put.michalxpz.core_ui.util.LocalSpacing

@Composable
fun RankingHistoryScreen(
    gameId: Int,
    onBackArrowPressed: () -> Unit,
    viewModel: RankingHistoryScreenViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state

    LaunchedEffect(key1 = viewModel) {
        viewModel.onEvent(RankingScreenEvent.OnInitGetGameFromDb(gameId))
    }

    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .height(spacing.extraLarge)
                .background(MaterialTheme.colorScheme.primary)
                .padding(spacing.mediumSmall)
        ) {
           Image(
               imageVector = Icons.Default.ArrowBack,
               contentDescription = "back_arrow",
               modifier = Modifier
                   .clickable {
                       onBackArrowPressed()
                   }
           )

            Spacer(modifier = Modifier.width(spacing.mediumSmall))

            Text(
                text = viewModel.state.gameRankingModel.title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier,
                maxLines = 1,
                softWrap = true
            )
        }


        Column(
            modifier = Modifier.fillMaxWidth().padding(spacing.mediumSmall),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(text = state.gameRankingModel.toString() ?: "", style = MaterialTheme.typography.labelSmall)
        }

    }

}