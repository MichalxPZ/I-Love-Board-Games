package pl.org.akai.game_list_presentation.game_list_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.poznan.put.michalxpz.core.R
import com.poznan.put.michalxpz.core_ui.util.LocalSpacing
import com.poznan.put.michalxpz.core_ui.util.UiText
import com.poznan.put.michalxpz.core_ui.util.utils.SortType

@Composable
fun ExpandableButton(
    options: List<ExpandableOption>,
    optionsCallbacks: List<() -> Unit>,
    isExpanded: Boolean,
    modifier: Modifier = Modifier,
    onExpandableButtonClick: () -> Unit
) {
    val spacing = LocalSpacing.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
            .border(BorderStroke(4.dp, MaterialTheme.colorScheme.primary),
                RoundedCornerShape(50.dp))
            .shadow(24.dp, RoundedCornerShape(50.dp))
            .background(MaterialTheme.colorScheme.onPrimary),
        verticalArrangement = Arrangement.Top
    ) {
        Button(
            onClick = { onExpandableButtonClick() },
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onPrimary),
        ) {
            Text(
                text = UiText.StringResource(R.string.Sort_by).asString(),
                style = MaterialTheme.typography.headlineSmall.copy(color = MaterialTheme.colorScheme.primary),
            )
        }

        AnimatedVisibility(visible = isExpanded) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                itemsIndexed(options) { index, option ->
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                            .clickable {
                                optionsCallbacks
                                    .get(index)
                                    .invoke()
                            }
                    ) {
                        Text(text = SortType.getString(option.sortType), modifier = Modifier.padding(start = spacing.extraLarge))
                        Image(imageVector = SortType.getImage(option.sortType), contentDescription = "", modifier = Modifier.padding(end = spacing.extraLarge), colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary))
                    }
                    Divider(modifier = Modifier,
                        thickness = 1.dp)
                    Divider(modifier = Modifier,
                        color = MaterialTheme.colorScheme.primary,
                        thickness = 1.dp)
                    Divider(modifier = Modifier,
                        thickness = 1.dp)
                }
            }
        }
    }

}