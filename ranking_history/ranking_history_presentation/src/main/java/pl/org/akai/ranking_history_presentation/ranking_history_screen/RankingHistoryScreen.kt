package pl.org.akai.ranking_history_presentation.ranking_history_screen

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.poznan.put.michalxpz.core_ui.util.LocalSpacing
import com.poznan.put.michalxpz.core_ui.util.UiText
import com.poznan.put.michalxpz.core.R
import pl.org.akai.ranking_history_presentation.ranking_history_screen.components.CollapsableToolbar
import pl.org.akai.ranking_history_presentation.ranking_history_screen.components.GraphView
import utils.DateFormater
import java.time.LocalDate

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RankingHistoryScreen(
    gameId: Int,
    onBackArrowPressed: () -> Unit,
    viewModel: RankingHistoryScreenViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state

    var progress by remember {
        mutableStateOf(0f)
    }

    LaunchedEffect(key1 = viewModel) {
        viewModel.onEvent(RankingScreenEvent.OnInitGetGameFromDb(gameId))
    }

    Column(
        modifier = Modifier.fillMaxSize(),
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
        }

        CollapsableToolbar(
            imageUrl = viewModel.state.gameRankingModel.imageUrl,
            title = viewModel.state.gameRankingModel.title
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Year: ${viewModel.state.gameRankingModel.year}",
                    style = MaterialTheme.typography.headlineMedium
                )
                
                Spacer(modifier = Modifier.height(spacing.mediumSmall))
                
                Text(
                    text = "Ranking Position: ${viewModel.state.gameRankingModel.rankingLatest}",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(spacing.mediumSmall))

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    items(viewModel.state.gameRankingModel.rankingCategories) { item ->
                        Card(modifier = Modifier
                            .padding(spacing.small)
                            .width(150.dp)
                            .height(40.dp)
                            .background(MaterialTheme.colorScheme.surface),
                            elevation = CardDefaults.cardElevation(4.dp),
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .width(150.dp)
                                    .height(40.dp)
                            ) {
                                Text(
                                    text = item,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    style = MaterialTheme.typography.headlineSmall,
                                )
                            }
                        }
                    }
                }

                val xAxis = viewModel.state.gameRankingModel.rankingHistoryDates.map { DateFormater.stringToDate(it) ?: LocalDate.MIN }
                val yAxis = viewModel.state.gameRankingModel.rankingHistoryPositions.map {
                    (it.toIntOrNull() ?: 0) * 100
                }

                Spacer(modifier = Modifier.height(spacing.mediumLarge))


//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(400.dp),
//                    contentAlignment = Alignment.Center
//                ) {
//                    GraphView(
//                        xAxis = xAxis,
//                        yAxis = yAxis
//                    )
//                }

                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(spacing.extraSmall),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = UiText.StringResource(R.string.Date).asString(),
                                style = MaterialTheme.typography.headlineMedium
                            )
                            Spacer(modifier = Modifier.width(spacing.mediumSmall))

                            Text(
                                text = UiText.StringResource(R.string.Position).asString(),
                                style = MaterialTheme.typography.headlineMedium
                            )
                        }
                    }
                    itemsIndexed(viewModel.state.gameRankingModel.rankingHistoryPositions.reversed()) { index, position ->
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(spacing.extraSmall),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = viewModel.state.gameRankingModel.rankingHistoryDates.reversed().get(index),
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Spacer(modifier = Modifier.width(spacing.mediumSmall))

                            Text(
                                text = position,
                                style = MaterialTheme.typography.headlineSmall
                            )
                        }
                    }
                }
            }
        }
    }
}