package dev.anirban.charts.linear.plots

import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.data.LinearDataStrategy

/**
 * This is the Line Plot class which implements the [LinearPlotterStrategy] Interface and makes a Line
 * Chart.
 *
 * Other implementation are [BarPlotStrategy] and [GradientPlotStrategy].
 *
 * @param lineStroke This defines the stroke of the line
 * @param circleRadius This defines the radius of curve of the Circle
 */
class LinePlotStrategy(
    private val lineStroke: Float = 3f,
    private val circleRadius: Float = 6f
) : LinearPlotterStrategy {

    /**
     * This function plots the graph data sets on the graph
     *
     * @param linearData The data of the graph [LinearDataStrategy] object implementation.
     * @param decoration The decoration of the graph [LinearDecoration] object implementation.
     */
    override fun DrawScope.plotChart(
        linearData: LinearDataStrategy,
        decoration: LinearDecoration
    ) {

        // This loop makes the curved line between two points
        linearData.linearDataSets.forEachIndexed { dataSetIndex, dataSet ->

            // Path Variable
            val path = Path()

            // Moving to the start path of the the coordinate set to start making the Curved lines
            path.moveTo(
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
                path.cubicTo(
                    x1 = control1X,
                    y1 = control1Y,
                    x2 = control2X,
                    y2 = control2Y,
                    x3 = nextPoint.x,
                    y3 = nextPoint.y
                )
            }

            // Drawing path after defining all the points of a single coordinate set in the path
            drawPath(
                path = path,
                color = decoration.plotPrimaryColor[dataSetIndex],
                style = Stroke(
                    width = lineStroke
                )
            )
        }

        // This loop draws the circles or the points of the coordinates1
        linearData.linearDataSets.forEachIndexed { index, dataSet ->
            dataSet.markers.forEach {
                // This function draws the Circle points
                drawCircle(
                    color = decoration.plotPrimaryColor[index],
                    radius = circleRadius,
                    center = it.offset
                )
            }
        }
    }
}