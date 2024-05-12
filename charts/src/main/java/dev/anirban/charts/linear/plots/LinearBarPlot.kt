package dev.anirban.charts.linear.plots

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.DrawScope
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.interfaces.LinearDataInterface
import dev.anirban.charts.linear.interfaces.LinearPlotInterface

/**
 * This is the Bar Plot class which implements the [LinearPlotInterface] Interface and makes a bar
 * Chart
 *
 * Other Implementations are [LinearGradientPlot] and [LinearLinePlot]
 *
 * @param barWidth This defines the width of the bars of the bar Chart
 * @param cornerRadius This defines the radius of curve of the corners of the bars
 */
class LinearBarPlot(
    private val barWidth: Float = 30f,
    private val cornerRadius: Float = 12f
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
        val paddingOffset = 12f

        // Adding the Offsets to the Variable
        linearData.linearDataSets.forEach { coordinateSet ->

            coordinateSet.markers.forEach { point ->

                // This function draws the Bars
                drawRoundRect(
                    brush = Brush.verticalGradient(
                        listOf(
                            decoration.plotPrimaryColor.first(),
                            decoration.plotPrimaryColor.last()
                        )
                    ),
                    topLeft = Offset(
                        x = point.x - barWidth / 2f,
                        y = point.y
                    ),
                    size = Size(
                        width = barWidth,
                        height = linearData.yAxisLabels.last().y - point.y - paddingOffset
                    ),
                    cornerRadius = CornerRadius(cornerRadius)
                )
            }
        }
    }
}