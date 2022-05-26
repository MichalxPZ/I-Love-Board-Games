package pl.org.akai.iloveboardgames.components.bottom_bar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.poznan.put.michalxpz.core_ui.util.LocalSpacing
import navigation.Routes
import java.util.*


@Composable
fun BgTabRow(
    allRoutes: List<String>,
    onTabSelected: (String) -> Unit,
    currentRoute: String,
    modifier: Modifier= Modifier
) {

    AnimatedVisibility(visible = currentRoute != Routes.ONBOARDING && !currentRoute.contains(Routes.RANKING_HISTORY)) {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .height(TabHeight),
            color = MaterialTheme.colorScheme.secondary
        ) {
            Row(
                modifier = Modifier.selectableGroup(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                allRoutes.forEach { screen ->

                    val icon = when (screen) {
                        Routes.PROFILE -> Icons.Outlined.Home
                        Routes.GAME_SCREEN -> Icons.Outlined.PlayArrow
                        Routes.SYNCHORNIZATION -> Icons.Outlined.Refresh
                        else -> Icons.Outlined.Person
                    }

                    RallyTab(
                        text = screen,
                        icon = icon,
                        onSelected = { onTabSelected(screen) },
                        selected = currentRoute == screen,

                    )
                }
            }
        }
    }
}

@Composable
private fun RallyTab(
    text: String,
    icon: ImageVector,
    onSelected: () -> Unit,
    selected: Boolean,
) {
    val spacing = LocalSpacing.current
    val color = MaterialTheme.colorScheme.onSecondary
    val durationMillis = if (selected) TabFadeInAnimationDuration else TabFadeOutAnimationDuration
    val animSpec = remember {
        tween<Color>(
            durationMillis = durationMillis,
            easing = LinearEasing,
            delayMillis = TabFadeInAnimationDelay
        )
    }
    val tabTintColor by animateColorAsState(
        targetValue = if (selected) color else color.copy(alpha = InactiveTabOpacity),
        animationSpec = animSpec
    )
    Row(
        modifier = Modifier
            .padding(spacing.mediumSmall)
            .animateContentSize()
            .height(TabHeight)
            .selectable(
                selected = selected,
                onClick = onSelected,
                role = Role.Tab,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = false,
                    radius = Dp.Unspecified,
                    color = Color.Unspecified
                )
            )
            .clearAndSetSemantics { contentDescription = text }
    ) {
        Icon(imageVector = icon, contentDescription = text, tint = tabTintColor)
        if (selected) {
            Spacer(Modifier.width(spacing.mediumSmall))
            Text(text.uppercase(Locale.getDefault()), color = tabTintColor)
        }
    }
}

private val TabHeight = 56.dp
private const val InactiveTabOpacity = 0.60f

private const val TabFadeInAnimationDuration = 150
private const val TabFadeInAnimationDelay = 100
private const val TabFadeOutAnimationDuration = 100