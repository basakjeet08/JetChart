package dev.anirban.charts.linear.data

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import dev.anirban.charts.linear.interfaces.LinearDataInterface
import dev.anirban.charts.util.Coordinate


/**
 * This is one of the implementation for storing and calculating the data in the chart. It
 * Implements the [LinearDataInterface] Interface
 *
 * @param xAxisLabels These are the readings of the X - Axis
 * @param dataSets These are the readings of the Y - Axis
 * @param yAxisLabels This is the list of marker which are present in the Y - Axis
 */
class LinearEmojiData(
    override val dataSets: List<DataSet>,
    override val xAxisLabels: List<Coordinate<String>>,
    override var yAxisLabels: MutableList<Coordinate<*>> = mutableListOf(),
    val dimension: Int = 50
) : LinearDataInterface {


    /**
     * These are the num of markers in X-Axis
     */
    override val numOfXLabels: Int = xAxisLabels.size
    override var numOfYLabels: Int = yAxisLabels.size


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

        // Storing the upper Label and Lower Label of Y Axis
        maxYLabel = yAxisLabels.size - 1
        minYLabel = 0

        // Difference between each Y Labels
        yLabelDifference = 1
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
        val yMarkerMaxWidth = calculateYLabelCoordinates()

        // X - Axis Scale
        xScale = (size.width - yMarkerMaxWidth) / numOfXLabels

        // This function calculates the Coordinates of the Markers
        calculateMarkersCoordinates(yMarkerMaxWidth = yMarkerMaxWidth)

        // This function calculates the Coordinates for the X - Labels
        calculateXLabelsCoordinates(size = size, yMarkerMaxWidth = yMarkerMaxWidth)
    }


    /**
     * This function calculates the Y - Axis Labels Coordinates
     */
    private fun calculateYLabelCoordinates(): Int {

        var maxDimension = 0

        // Calculating all the chart Y - Axis Labels in the chart along with their coordinates
        yAxisLabels.forEachIndexed { index, point ->

            point.value as BitmapDrawable

            // Current Y Coordinate for the point
            val currentYCoordinate = (yScale * index) - (dimension.toFloat() / 2f)

            val resizedBitmap =
                Bitmap.createScaledBitmap(point.value.bitmap, dimension, dimension, true)

            if (resizedBitmap.width > maxDimension)
                maxDimension = resizedBitmap.width

            // Setting the calculated graph coordinates to the object
            point.setOffset(x = -24f, y = currentYCoordinate)
        }
        return maxDimension
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

                val currentYCoordinate = (maxYLabel - point.value) * yScale / yLabelDifference
                val currentXCoordinate = xAxisOffset + (index * xScale) + yMarkerMaxWidth

                // Setting the calculated graph coordinates to the object
                point.setOffset(x = currentXCoordinate, y = currentYCoordinate)
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