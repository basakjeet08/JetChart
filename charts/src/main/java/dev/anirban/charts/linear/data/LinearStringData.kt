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
 * This is one of the implementation for storing and calculating the data in the chart. It
 * implements the [LinearDataInterface].
 *
 * Purpose :- Providing a way to calculate the necessary data required to show the chart and
 * store them. It also calculates the graphical coordinates of the data.
 *
 * @param dataSets This is the dataset of the Chart.
 * @param xAxisLabels These are the labels of the X - Axis
 * @param yAxisLabels This is the list of Y axis Labels.
 * @param numOfYLabels These are the num of labels in Y-axis
 */
class LinearStringData(
    override val dataSets: List<DataSet>,
    override val xAxisLabels: List<Coordinate<String>>,
    override var yAxisLabels: MutableList<Coordinate<*>> = mutableListOf(),
    override var numOfYLabels: Int = 5
) : LinearDataInterface {


    /**
     * These are the num of labels in X-Axis
     */
    override val numOfXLabels: Int = xAxisLabels.size


    /**
     * The Maximum Y Label Reading of the Graph
     */
    private var maxYLabel: Int = Int.MIN_VALUE


    /**
     * The Minimum Y Label Reading of the Graph
     */
    private var minYLabel: Int = Int.MAX_VALUE


    /**
     * It is the difference between each Y Label from its following one.
     */
    private var yLabelDifference: Int


    /**
     * This is the offset of the X Axis from the initial starting point.
     */
    private val xAxisOffset: Float = 48f


    /**
     * This are the X and Y Scales for the Graph
     */
    private var xScale: Float = 0f
    private var yScale: Float = 0f


    init {

        // Checking if the markers are provided or we need to calculate
        when (yAxisLabels.isNotEmpty()) {
            true -> {

                numOfYLabels = yAxisLabels.size

                // Storing the upper Label and Lower Label of Y Axis
                maxYLabel = yAxisLabels.size - 1
                minYLabel = 0

                // Difference between each Y Labels
                yLabelDifference = 1
            }

            false -> {

                // Maximum and minimum value provided is calculated
                val yMax = dataSets.maxOf { it.max }
                val yMin = dataSets.minOf { it.min }


                // Storing the upper Label and Lower Label of Y Axis
                maxYLabel = if (yMax % (numOfYLabels - 1) != 0.0f)
                    yMax.toInt() + ((numOfYLabels - 1) - (yMax.toInt() % (numOfYLabels - 1)))
                else
                    yMax.toInt()

                minYLabel = if (yMin.toInt() % (numOfYLabels - 1) == 0)
                    yMin.toInt() - (numOfYLabels - 1)
                else
                    yMin.toInt() - (yMin.toInt() % (numOfYLabels - 1))


                // Difference between each Y Label
                yLabelDifference = (maxYLabel - minYLabel) / (numOfYLabels - 1)

                // Calculating the points for Y - Axis labels
                for (index in 0 until numOfYLabels) {

                    // This is the value of the current Y Axis labels
                    val currentYLabel = maxYLabel - (index) * yLabelDifference
                    yAxisLabels.add(index, Coordinate(currentYLabel))
                }
            }
        }
    }


    /**
     * This is the function which is responsible for the calculations of all the graph related stuff
     *
     * @param size This is the size of the whole canvas which also haves the componentSize in it
     */
    override fun DrawScope.doCalculations(size: Size) {

        // Scale of Y - Axis of the Graph
        yScale = size.height / numOfYLabels

        // maximum Width of the Y - Labels. Needs Y Scale
        val yLabelMaxWidth = calculateYLabelsCoordinates()

        // X - Axis Scale
        xScale = (size.width - yLabelMaxWidth) / numOfXLabels

        // This function calculates the Coordinates of the Markers
        calculateMarkersCoordinates(yMarkerMaxWidth = yLabelMaxWidth)

        // This function calculates the Coordinates for the X - Labels
        calculateXLabelsCoordinates(size = size, yMarkerMaxWidth = yLabelMaxWidth)
    }


    /**
     * This function calculates the Y - Axis Labels Coordinates
     */
    private fun DrawScope.calculateYLabelsCoordinates(): Int {

        var yLabelMaxWidth = 0

        // Calculating all the chart Y - Axis Labels in the chart along with their coordinates
        yAxisLabels.forEachIndexed { index, point ->

            with(point) {
                val bounds = Rect()
                val paint = Paint()

                paint.textSize = 12.sp.toPx()
                paint.textAlign = Paint.Align.LEFT
                paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
                paint.getTextBounds(value.toString(), 0, value.toString().length, bounds)

                // Current Y Coordinate for the point
                val currentYCoordinate = (yScale * index) + 12f

                // Setting the calculated graph coordinates to the object
                setOffset(x = -24f, currentYCoordinate)

                val width = bounds.width()
                yLabelMaxWidth = if (yLabelMaxWidth < width) width else yLabelMaxWidth
            }
        }
        return yLabelMaxWidth
    }


    /**
     * This function calculates the Coordinates for the Markers
     *
     * @param yMarkerMaxWidth This is the maximum width of the Y - Labels
     */
    private fun calculateMarkersCoordinates(yMarkerMaxWidth: Int) {

        // Taking all the points given and calculating where they will stay in the graph
        dataSets.forEach { pointSet ->

            pointSet.forEachIndexed { index, point ->
                with(point) {

                    val currentYCoordinate = (maxYLabel - value) * yScale / yLabelDifference
                    val currentXCoordinate = xAxisOffset + (index * xScale) + yMarkerMaxWidth

                    // Setting the calculated graph coordinates to the object
                    setOffset(x = currentXCoordinate, y = currentYCoordinate)
                }
            }
        }
    }


    /**
     * This Function calculates the coordinates for the X Labels
     *
     * @param size This is the size of the canvas
     * @param yMarkerMaxWidth This is the maximum width of the Y - Labels
     */
    private fun calculateXLabelsCoordinates(size: Size, yMarkerMaxWidth: Int) {

        // Calculating all the chart X - Axis Labels coordinates
        xAxisLabels.forEachIndexed { index, currentMarker ->

            val xCoordinate = (xScale * index) + xAxisOffset + yMarkerMaxWidth
            val yCoordinate = size.height

            // Setting the calculated graph coordinates to the object
            currentMarker.setOffset(x = xCoordinate, y = yCoordinate)
        }
    }
}