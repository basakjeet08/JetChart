package dev.anirban.charts.linear.data

import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.sp
import dev.anirban.charts.linear.interfaces.LinearDataInterface
import dev.anirban.charts.util.Coordinate


/**
 * This class implements the [LinearDataInterface] and stores the data necessary for a linear chart.
 * For other implementations check [LinearEmojiData].
 *
 * @param linearDataSets This is the data set of the chart.
 * @param xAxisLabels These are the labels for the X - Axis.
 * @param yAxisLabels These are the labels for the Y - Axis.
 * @param numOfYLabels These are the num of labels in Y-axis
 */
class LinearStringData(
    override val linearDataSets: List<LinearDataSet>,
    override val xAxisLabels: List<Coordinate<String>>,
    override var yAxisLabels: MutableList<Coordinate<*>> = mutableListOf(),
    override var numOfYLabels: Int = 5
) : LinearDataInterface {


    /**
     * These are the count of labels in X-Axis
     */
    override val numOfXLabels: Int = xAxisLabels.size


    /**
     * The maximum or peak Y Label of the Graph.
     */
    override var maxYLabel: Int = Int.MIN_VALUE


    /**
     * The Minimum Y Label Reading of the Graph
     */
    override var minYLabel: Int = Int.MAX_VALUE


    /**
     * This is the difference between each Y label and its subsequent label.
     */
    override var yLabelDifference: Int


    /**
     * This is the offset of the X Axis from the initial starting point.
     */
    override val xAxisOffset: Float = 48f


    /**
     * These are the X and Y Scales for the graph.
     */
    override var xScale: Float = 0f
    override var yScale: Float = 0f


    init {

        // Checking if the markers are provided or we need to calculate
        when (yAxisLabels.isNotEmpty()) {
            true -> {

                numOfYLabels = yAxisLabels.size

                // Storing the maximum Label and minimum Label of Y Axis
                maxYLabel = yAxisLabels.size - 1
                minYLabel = 0

                // Difference between each Y label to its subsequent label
                yLabelDifference = 1
            }

            false -> {

                // Maximum and minimum value provided
                val yMax = linearDataSets.maxOf { it.max }
                val yMin = linearDataSets.minOf { it.min }


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
    }


    /**
     * This is the function responsible for all the graph related calculations.
     *
     * @param size This is the size of the whole canvas.
     */
    override fun DrawScope.doCalculations(size: Size) {

        // Scale of Y Axis of the graph
        yScale = size.height / numOfYLabels

        // Maximum width of the Y labels. Needs y Scale to be calculated beforehand.
        val yLabelMaxWidth = calculateYLabelsCoordinates()

        // X - Axis Scale
        xScale = (size.width - yLabelMaxWidth) / numOfXLabels

        // This function calculates the offset of the markers/observation in the graph
        calculateMarkersCoordinates(yLabelMaxWidth = yLabelMaxWidth)

        // This function calculates the offset for the X labels in the graph
        calculateXLabelsCoordinates(size = size, yLabelsMaxWidth = yLabelMaxWidth)
    }


    /**
     * This function calculates the Y axis labels offsets.
     */
    override fun DrawScope.calculateYLabelsCoordinates(): Int {

        var yLabelMaxWidth = 0

        // Calculating all the Y axis labels in the chart along with their offset.
        yAxisLabels.forEachIndexed { index, point ->

            with(point) {
                val bounds = Rect()
                val paint = Paint()

                paint.textSize = 12.sp.toPx()
                paint.textAlign = Paint.Align.LEFT
                paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
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


    /**
     * This function calculates the offset for the markers or observation or data set.
     *
     * @param yLabelMaxWidth This is the maximum width of the Y labels.
     */
    override fun calculateMarkersCoordinates(yLabelMaxWidth: Int) {

        // Taking all the observations given and calculating their offset.
        linearDataSets.forEach { pointSet ->

            pointSet.forEachIndexed { index, point ->
                with(point) {

                    val currentYCoordinate = (maxYLabel - value) * yScale / yLabelDifference
                    val currentXCoordinate = xAxisOffset + (index * xScale) + yLabelMaxWidth

                    // Setting the calculated graph offset to the object
                    setOffset(x = currentXCoordinate, y = currentYCoordinate)
                }
            }
        }
    }


    /**
     * This function calculates the offset for the X labels
     *
     * @param size This is the size of the canvas
     * @param yLabelsMaxWidth This is the maximum width of the Y labels
     */
    override fun calculateXLabelsCoordinates(size: Size, yLabelsMaxWidth: Int) {

        // Calculating all the chart X axis labels offset
        xAxisLabels.forEachIndexed { index, currentMarker ->

            val xCoordinate = (xScale * index) + xAxisOffset + yLabelsMaxWidth
            val yCoordinate = size.height

            // Setting the calculated graph offsets to the object
            currentMarker.setOffset(x = xCoordinate, y = yCoordinate)
        }
    }
}