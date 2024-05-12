package dev.anirban.charts.linear.axis

import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.sp
import dev.anirban.charts.linear.data.LinearDataSet
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.interfaces.LinearAxis
import dev.anirban.charts.linear.interfaces.LinearLabelDrawerInterface
import dev.anirban.charts.linear.labels.LinearStringLabelDrawer
import dev.anirban.charts.util.Coordinate

class LinearStringAxis(
    override val xTitle: Coordinate<String> = Coordinate(""),
    override val yTitle: Coordinate<String> = Coordinate(""),
    override val xAxisLabels: List<Coordinate<String>>,
    override var yAxisLabels: MutableList<Coordinate<*>> = mutableListOf(),
    override var numOfYLabels: Int = 5,
    labelDrawer: LinearStringLabelDrawer = LinearStringLabelDrawer()
) : LinearAxis {

    override val labelDrawer: LinearLabelDrawerInterface = labelDrawer
    override val numOfXLabels: Int = xAxisLabels.size

    private var maxYLabel: Int = Int.MIN_VALUE
    private var minYLabel: Int = Int.MAX_VALUE
    private var yLabelDifference: Int = 0

    private var xScale: Float = 0f
    private var yScale: Float = 0f

    /**
     * This is the offset of the X Axis from the initial starting point.
     */
    private val xAxisOffset: Float = 48f


    override fun DrawScope.drawXLabels(decoration: LinearDecoration) {

        // This Draws the Y Markers below the Graph
        xAxisLabels.forEach { currentMarker ->

            // This draws the String Marker
            drawContext.canvas.nativeCanvas.drawText(
                currentMarker.value,
                currentMarker.x,
                currentMarker.y,
                Paint().apply {
                    color = decoration.textColor.toArgb()
                    textSize = 12.sp.toPx()
                    textAlign = Paint.Align.CENTER
                    typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
                }
            )
        }
    }

    override fun DrawScope.drawYLabels(decoration: LinearDecoration) {

        yAxisLabels.forEach { point ->

            val bounds = Rect()
            val paint = Paint().apply {
                color = decoration.textColor.toArgb()
                textSize = 12.sp.toPx()
                textAlign = Paint.Align.LEFT
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
            }
            paint.getTextBounds(point.value.toString(), 0, point.value.toString().length, bounds)

            val width = bounds.width()

            // This draws the String Marker
            drawContext.canvas.nativeCanvas.drawText(
                point.value.toString(),
                point.x,
                point.y,
                paint
            )

            // This draws the Lines for the readings parallel to X Axis
            drawLine(
                start = Offset(
                    x = width.toFloat(),
                    y = point.y - 12f
                ),
                color = decoration.textColor.copy(alpha = 0.8f),
                end = Offset(
                    x = size.width,
                    y = point.y - 12f
                ),
                strokeWidth = 1f
            )
        }
    }

    override fun DrawScope.startCalculation(size: Size, dataSet: List<LinearDataSet>) {
        when {
            yAxisLabels.isNotEmpty() -> {

                numOfYLabels = yAxisLabels.size

                // Storing the maximum Label and minimum Label of Y Axis
                maxYLabel = yAxisLabels.size - 1
                minYLabel = 0

                // Difference between each Y label to its subsequent label
                yLabelDifference = 1
            }

            else -> {

                // Maximum and minimum value provided
                val yMax = dataSet.maxOf { it.max }
                val yMin = dataSet.minOf { it.min }


                // Storing the maximum and minimum Label of Y Axis
                maxYLabel = if (yMax % (numOfYLabels - 1) != 0.0f)
                    yMax.toInt() + ((numOfYLabels - 1) - (yMax.toInt() % (numOfYLabels - 1)))
                else
                    yMax.toInt()
                minYLabel = if (yMin.toInt() % (numOfYLabels - 1) == 0)
                    yMin.toInt() - (numOfYLabels - 1)
                else
                    yMin.toInt() - (yMin.toInt() % (numOfYLabels - 1))


                // Difference between each Y label to its subsequent label
                yLabelDifference = (maxYLabel - minYLabel) / (numOfYLabels - 1)

                // Calculating the values of Y - Axis labels
                for (index in 0 until numOfYLabels) {

                    // This is the value of the current Y Axis labels
                    val currentYLabel = maxYLabel - (index) * yLabelDifference
                    yAxisLabels.add(index, Coordinate(currentYLabel))
                }
            }
        }

        // Scale of Y Axis of the graph
        yScale = size.height / numOfYLabels

        // Maximum width of the Y labels. Needs y Scale to be calculated beforehand.
        val yLabelMaxWidth = calculateYLabelsCoordinates()

        // X - Axis Scale
        xScale = (size.width - yLabelMaxWidth) / numOfXLabels

        // This function calculates the offset for the X labels in the graph
        calculateXLabelsCoordinates(size = size, yLabelsMaxWidth = yLabelMaxWidth)
    }

    override fun DrawScope.calculateYLabelsCoordinates(): Int {

        // Maximum width of the Y labels.
        var yLabelMaxWidth = 0

        // Calculating all the Y axis labels in the chart along with their offset.
        yAxisLabels.forEachIndexed { index, point ->

            with(point) {
                val bounds = Rect()
                val paint = Paint().apply {
                    textSize = 12.sp.toPx()
                    textAlign = Paint.Align.LEFT
                    typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
                }
                paint.getTextBounds(value.toString(), 0, value.toString().length, bounds)

                // Current Y offset for the point.
                val currentYCoordinate = (yScale * index) + 12f

                // Setting the calculated graph offset of the object.
                setOffset(x = -24f, currentYCoordinate)

                val width = bounds.width()
                yLabelMaxWidth = if (yLabelMaxWidth < width) width else yLabelMaxWidth
            }
        }
        return yLabelMaxWidth
    }

    override fun DrawScope.calculateXLabelsCoordinates(size: Size, yLabelsMaxWidth: Int) {

        // Calculating all the chart X axis labels offset
        xAxisLabels.mapIndexed { index, coordinate ->

            val xCoordinate = (xScale * index) + xAxisOffset + yLabelsMaxWidth
            val yCoordinate = size.height

            // Setting the calculated graph offsets to the object
            coordinate.setOffset(x = xCoordinate, y = yCoordinate)
        }
    }
}