package dev.anirban.charts.linear.plots

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import dev.anirban.charts.linear.data.LinearEmojiData
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.interfaces.LinearDataInterface
import dev.anirban.charts.linear.interfaces.LinearPlotInterface

/**
 * This is the gradient plot class which implements the [LinearPlotInterface] Interface and makes
 * a gradient line chart.
 *
 * Other implementation are [LinearBarPlot] and [LinearLinePlot]
 *
 * @param lineStroke This defines the stroke of the line
 * @param circleRadius This defines the radius of curve of the Circle
 * @param colorList This is the list of colors for the gradient
 */
class LinearGradientPlot(
    private val lineStroke: Float = 3f,
    private val circleRadius: Float = 6f,
    private val colorList: List<Color>
) : LinearPlotInterface {


    /**
     * This function plots the graph data sets on the graph
     *
     * @param linearData The data of the graph [LinearDataInterface] object implementation.
     * @param decoration The decoration of the graph [LinearDecoration] object implementation.
     */
    override fun DrawScope.plotChart(
        linearData: LinearDataInterface,
        decoration: LinearDecoration
    ) {

        // Padding Offset that would be negated from the bar height to make it align with the chart
        var paddingOffset = 12f

        // If the data are in form of emoji's then the padding offset will change
        if (linearData is LinearEmojiData) {
            paddingOffset = -(linearData.dimension.toFloat() / 2f)
        }

        // This loop makes the curved line between two points
        linearData.linearDataSets.forEachIndexed { coordinateSetIndex, dataSet ->

            // Path Variables
            val linePath = Path()
            val gradientPath = Path()

            // Moving to the start line path of the the coordinate set to start making the Curved lines
            linePath.moveTo(
                dataSet.markers[0].x,
                dataSet.markers[0].y
            )

            // Moving the gradient path to the (0,0) Origin Of the Graph
            gradientPath.moveTo(
                x = linearData.xAxisLabels.first().x,
                y = linearData.yAxisLabels.last().y - paddingOffset
            )

            // Moving the gradient path to the first point of the coordinate set
            gradientPath.lineTo(
                dataSet.markers[0].x,
                dataSet.markers[0].y
            )

            // Inner Loop which draws the Lines from point to point of a single coordinate sets
            for (index in 0 until dataSet.size - 1) {

                // Points needed
                val currentPoint = dataSet.markers[index]
                val nextPoint = dataSet.markers[index + 1]

                // Control Points
                val control1X = (currentPoint.x + nextPoint.x) / 2f
                val control1Y = currentPoint.y
                val control2X = (currentPoint.x + nextPoint.x) / 2f
                val control2Y = nextPoint.y

                // Defining the path from the last stayed to the next point
                linePath.cubicTo(
                    x1 = control1X,
                    y1 = control1Y,
                    x2 = control2X,
                    y2 = control2Y,
                    x3 = nextPoint.x,
                    y3 = nextPoint.y
                )
                gradientPath.cubicTo(
                    x1 = control1X,
                    y1 = control1Y,
                    x2 = control2X,
                    y2 = control2Y,
                    x3 = nextPoint.x,
                    y3 = nextPoint.y
                )
            }

            // Drawing the Lines of the graph
            drawPath(
                path = linePath,
                color = decoration.plotPrimaryColor[coordinateSetIndex],
                style = Stroke(width = lineStroke)
            )

            // Moving the Gradient Path to the X Axis of the last point in the coordinate set
            gradientPath.lineTo(
                x = linearData.xAxisLabels.last().x,
                y = linearData.yAxisLabels.last().y - paddingOffset
            )

            // Defining the Brush
            val brush = Brush.verticalGradient(
                colors = colorList,
                tileMode = TileMode.Clamp
            )

            // Drawing the gradient of the graph
            drawPath(
                path = gradientPath,
                brush = brush
            )
        }

        // Drawing all the Circles in this pass
        linearData.linearDataSets.forEachIndexed { index, dataSet ->
            dataSet.markers.forEach { point ->

                // This function draws the Circle points
                drawCircle(
                    color = decoration.plotSecondaryColor[index],
                    radius = circleRadius,
                    center = point.offset()
                )
            }
        }
    }
}