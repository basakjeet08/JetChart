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
 * This is the Line Plot class which implements the [LinearPlotInterface] Interface and makes a Line
 * Chart
 *
 * @param lineStroke This defines the stroke of the line
 * @param circleRadius This defines the radius of curve of the Circle
 * @param colorList This is the list of colors for the gradient
 */
class LinearGradientLinePlot(
    private val lineStroke: Float = 3f,
    private val circleRadius: Float = 6f,
    private val colorList: List<Color>
) : LinearPlotInterface {

    /**
     * This is the function which contains the actual margin implementation
     *
     * @param linearData This is the data of the Line Chart
     * @param decoration THis is the decoration of the function
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

        // Drawing the Lines and the linear Gradient in this Iteration
        linearData.yAxisReadings.forEachIndexed { i, coordinateSet ->

            // Path Variable
            val path = Path()

            // Moving to the (0,0) Origin Of the Graph
            path.moveTo(
                linearData.xAxisReadings.first().xCoordinate,
                linearData.yMarkerList.last().yCoordinate - paddingOffset
            )

            // Defining the Brush
            val brush = Brush.horizontalGradient(
                colors = colorList,
                startX = linearData.xAxisReadings.first().xCoordinate,
                endX = linearData.xAxisReadings.last().xCoordinate,
                tileMode = TileMode.Clamp
            )

            // joining all the lines in the Graph to each other in sequence
            coordinateSet.forEach { point ->

                // Current Line Point Added
                path.lineTo(
                    x = point.xCoordinate,
                    y = point.yCoordinate
                )
            }

            // Adding the (Max , 0) point to finish the gradient in a proper sequence
            path.lineTo(
                linearData.xAxisReadings.last().xCoordinate,
                linearData.yMarkerList.last().yCoordinate - paddingOffset
            )

            // Drawing the Lines of the graph
            drawPath(
                path = path,
                color = decoration.plotPrimaryColor[i],
                style = Stroke(
                    width = lineStroke
                )
            )

            // Drawing the gradient of the graph
            drawPath(
                path = path,
                brush = brush
            )
        }

        // Drawing all the Circles in this pass
        linearData.yAxisReadings.forEachIndexed { index, linearPoints ->
            linearPoints.forEach { point ->

                // This function draws the Circle points
                drawCircle(
                    color = decoration.plotSecondaryColor[index],
                    radius = circleRadius,
                    center = point.getOffset()
                )
            }
        }
    }
}