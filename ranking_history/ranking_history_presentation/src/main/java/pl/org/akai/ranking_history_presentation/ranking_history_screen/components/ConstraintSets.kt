package pl.org.akai.ranking_history_presentation.ranking_history_screen.components

import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

fun startConstraintSet() = ConstraintSet {
    val poster = createRefFor("poster")
    val title = createRefFor("title")
    val content = createRefFor("content")

    constrain(poster) {
        width = Dimension.fillToConstraints
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        top.linkTo(parent.top)
    }

    constrain(title) {
        start.linkTo(parent.start, 16.dp)
        top.linkTo(poster.bottom, 16.dp)
    }

    constrain(content) {
        width = Dimension.fillToConstraints
        top.linkTo(title.bottom, 8.dp)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }
}

fun endConstraintSet() = ConstraintSet {
    val poster = createRefFor("poster")
    val title = createRefFor("title")
    val content = createRefFor("content")

    constrain(poster) {
        width = Dimension.fillToConstraints
        height = Dimension.value(56.dp)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        top.linkTo(parent.top)
    }

    constrain(title) {
        start.linkTo(parent.start)
        top.linkTo(parent.top, 8.dp)
        end.linkTo(parent.end)
        bottom.linkTo(poster.bottom)
    }

    constrain(content) {
        width = Dimension.fillToConstraints
        top.linkTo(poster.bottom, 8.dp)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }
}