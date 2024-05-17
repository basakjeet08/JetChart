package dev.anirban.charts.circular.foreground

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.data.CircularDataStrategy

/**
 * This class implements the [CircularForegroundStrategy] which provides a strategy for creation of
 * a basic donut Chart.
 *
 * For other implementations see [DonutTargetForegroundStrategy]
 *
 * @param radiusMultiplier This is the multiplier to radius of the circle to make the Arcs bigger.
 * @param strokeWidth This is the width of the stroke of the Arc
 * @param startAngle This defines the starting angle of the Chart Arc
 */
class DonutForegroundStrategy(
    private val radiusMultiplier: Float = 1.4f,
    private val strokeWidth: Float = 45f,
    private val startAngle: Float = 270f
) : CircularForegroundStrategy {


    /**
     * This function draws the foreground according to the strategy
     *
     * @param circularData This is the data of the chart
     * @param decoration This is the decoration of the chart
     */
    override fun DrawScope.drawForeground(
        circularData: CircularDataStrategy,
        decoration: CircularDecoration
    ) {

        val centerX = size.width / 2
        val centerY = size.height / 2
        val radius = (size.width / 4).coerceAtMost(size.height / 4) * radiusMultiplier

        val arcRect = Rect(
            centerX - radius,
            centerY - radius,
            centerX + radius,
            centerY + radius
        )

        // This is used to define the sweep angle of each and every Floating Data
        var currentSweepAngle = startAngle

        // Drawing all the arcs
        circularData.sweepAngles.forEachIndexed { index, fl ->

            //This function draws the Arc
            drawArc(
                color = decoration.colorList[index],
                startAngle = currentSweepAngle,
                sweepAngle = fl,
                useCenter = false,
                size = arcRect.size,
                style = Stroke(
                    width = strokeWidth
                ),
                topLeft = arcRect.topLeft
            )

            // Marking the sweep angle for the next Floating Item
            currentSweepAngle += fl + 4f
        }
    }
}