package dev.anirban.charts.circular.foreground

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.interfaces.CircularDataInterface
import dev.anirban.charts.circular.interfaces.CircularForegroundInterface

/**
 * This class implements the [CircularForegroundInterface] which is responsible for making the
 * foreground reading of the chart
 *
 * @param radiusMultiplier This is the multiplier to radius of the circle to make the Arcs a bit
 * bigger... Note :- Higher this value bigger the radius
 * @param strokeWidth This is the width of the stroke of the Arc
 * @param startAngle This defines the starting angle of the Chart Arc
 */
class CircularRingForeground(
    private val radiusMultiplier: Float = 1.5f,
    private val strokeWidth: Float = 30f,
    private val startAngle: Float = 120f
) : CircularForegroundInterface {

    /**
     * This is the function which draws all the readings
     *
     * @param circularData This is the data of the chart
     * @param decoration This is the decoration of the chart
     */
    override fun DrawScope.drawForeground(
        circularData: CircularDataInterface,
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

        //This function draws the Background Arc
        drawArc(
            color = Color.LightGray.copy(alpha = .2f),
            startAngle = startAngle,
            sweepAngle = 300f,
            useCenter = false,
            size = arcRect.size,
            style = Stroke(
                width = strokeWidth,
                cap = StrokeCap.Round
            ),
            topLeft = arcRect.topLeft
        )

        //This function draws the Foreground Arc
        drawArc(
            brush = Brush.linearGradient(
                colors = decoration.colorList,
                start = arcRect.bottomLeft,
                end = arcRect.topRight
            ),
            startAngle = startAngle,
            sweepAngle = circularData.sweepAngles.first(),
            useCenter = false,
            size = arcRect.size,
            style = Stroke(
                width = strokeWidth,
                cap = StrokeCap.Round
            ),
            topLeft = arcRect.topLeft
        )
    }
}