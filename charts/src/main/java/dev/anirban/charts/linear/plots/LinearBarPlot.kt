package dev.anirban.charts.linear.plots

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.DrawScope
import dev.anirban.charts.linear.data.LinearEmojiData
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.interfaces.LinearDataInterface
import dev.anirban.charts.linear.interfaces.LinearPlotInterface

/**
 * This is the Line Plot class which implements the [LinearPlotInterface] Interface and makes a bar
 * Chart
 *
 * @param barWidth This defines the width of the bars of the bar Chart
 * @param cornerRadius This defines the radius of curve of the corners of the bars
 */
class LinearBarPlot(
    private val barWidth: Float = 30f,
    private val cornerRadius: Float = 12f
) : LinearPlotInterface {

    /**
     * This function plots the Bar Chart in the canvas
     *
     * @param linearData This is the data object which contains the data of the whole graph
     * @param decoration This object contains the decorations for the chart
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

        // Adding the Offsets to the Variable
        linearData.dataSets.forEach { coordinateSet ->

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
                        height = linearData.yMarkerList.last().y - point.y - paddingOffset
                    ),
                    cornerRadius = CornerRadius(cornerRadius)
                )
            }
        }
    }
}