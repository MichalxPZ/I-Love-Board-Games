package pl.org.akai.ranking_history_presentation.ranking_history_screen.components


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.BrushPainter
import me.bytebeats.views.charts.line.LineChart
import me.bytebeats.views.charts.line.LineChartData
import me.bytebeats.views.charts.line.LineChartData.Point
import me.bytebeats.views.charts.line.render.line.SolidLineDrawer
import me.bytebeats.views.charts.line.render.point.FilledCircularPointDrawer
import me.bytebeats.views.charts.line.render.xaxis.SimpleXAxisDrawer
import me.bytebeats.views.charts.line.render.yaxis.SimpleYAxisDrawer
import me.bytebeats.views.charts.simpleChartAnimation
import utils.DateFormater
import java.time.LocalDate

@Composable
fun GraphView(
    xAxis: List<LocalDate>,
    yAxis: List<Int>,
    modifier: Modifier = Modifier,
) {

    val maxY = (yAxis.maxOrNull() ?: 300)
    val minY = (yAxis.minOrNull() ?: 1) + 1

    if (xAxis.isEmpty()) return

    Canvas(modifier = modifier) {
        // Total number of transactions.
        val totalRecords = xAxis.size

        // Maximum distance between dots (transactions)
        val lineDistance = size.width / (totalRecords + 1)

        // Canvas height
        val cHeight = size.height

        // Add some kind of a "Padding" for the initial point where the line starts.
        var currentLineDistance = 0F + lineDistance

        xAxis.forEachIndexed { index, date ->
            if (totalRecords >= index + 2) {
                drawLine(
                    start = Offset(
                        x = currentLineDistance,
                        y = calculateYCoordinate(
                            higherTransactionRateValue = maxY.toDouble(),
                            currentTransactionRate = yAxis.get(index).toDouble(),
                            canvasHeight = cHeight
                        )
                    ),
                    end = Offset(
                        x = currentLineDistance + lineDistance,
                        y = calculateYCoordinate(
                            higherTransactionRateValue = maxY.toDouble(),
                            currentTransactionRate = yAxis[index + 1].toDouble(),
                            canvasHeight = cHeight
                        )
                    ),
                    color = Color(255, 0, 0, 255),
                    strokeWidth = Stroke.DefaultMiter
                )
            }
            currentLineDistance += lineDistance
        }
    }
}

private fun calculateYCoordinate(
    higherTransactionRateValue: Double,
    currentTransactionRate: Double,
    canvasHeight: Float
): Float {
    val maxAndCurrentValueDifference = (higherTransactionRateValue - currentTransactionRate)
        .toFloat()
    val relativePercentageOfScreen = (canvasHeight / higherTransactionRateValue)
        .toFloat()
    return maxAndCurrentValueDifference * relativePercentageOfScreen
}