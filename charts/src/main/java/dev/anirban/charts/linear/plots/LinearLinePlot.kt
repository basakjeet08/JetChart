package dev.anirban.charts.linear.plots

import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.interfaces.LinearDataInterface
import dev.anirban.charts.linear.interfaces.LinearPlotInterface

/**
 * This is the Line Plot class which implements the [LinearPlotInterface] Interface and makes a Line
 * Chart
 *
 * @param lineStroke This defines the stroke of the line
 * @param circleRadius This defines the radius of curve of the Circle
 */
class LinearLinePlot(
    private val lineStroke: Float = 3f,
    private val circleRadius: Float = 6f
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

        // This loop makes the curved line between two points
        linearData.yAxisReadings.forEachIndexed { coordinateSetIndex, coordinateSet ->

            // Path Variable
            val path = Path()

            // Moving to the start path of the the coordinate set to start making the Curved lines
            path.moveTo(
                coordinateSet[0].xCoordinate,
                coordinateSet[0].yCoordinate
            )

            // Inner Loop which draws the Lines from point to point of a single coordinate sets
            for (index in 0 until coordinateSet.size - 1) {

                // Points needed
                val currentPoint = coordinateSet[index]
                val nextPoint = coordinateSet[index + 1]

                // Control Points
                val control1X = (currentPoint.xCoordinate + nextPoint.xCoordinate) / 2f
                val control1Y = currentPoint.yCoordinate
                val control2X = (currentPoint.xCoordinate + nextPoint.xCoordinate) / 2f
                val control2Y = nextPoint.yCoordinate

                // Defining the path from the last stayed to the next point
                path.cubicTo(
                    x1 = control1X,
                    y1 = control1Y,
                    x2 = control2X,
                    y2 = control2Y,
                    x3 = nextPoint.xCoordinate,
                    y3 = nextPoint.yCoordinate
                )
            }

            // Drawing path after defining all the points of a single coordinate set in the path
            drawPath(
                path = path,
                color = decoration.plotPrimaryColor[coordinateSetIndex],
                style = Stroke(
                    width = lineStroke
                )
            )
        }

        // This loop draws the circles or the points of the coordinates1
        linearData.yAxisReadings.forEachIndexed { index, offsets ->
            offsets.forEach {
                // This function draws the Circle points
                drawCircle(
                    color = decoration.plotSecondaryColor[index],
                    radius = circleRadius,
                    center = it.getOffset()
                )
            }
        }
    }
}