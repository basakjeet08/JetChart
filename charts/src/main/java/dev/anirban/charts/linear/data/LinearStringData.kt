package dev.anirban.charts.linear.data

import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.sp
import dev.anirban.charts.linear.interfaces.LinearDataInterface
import dev.anirban.charts.util.ChartPoint

/**
 * This is one of the implementation for storing and calculating the data in the chart. It
 * Implements the [LinearDataInterface] Interface
 *
 * @param xAxisReadings These are the readings of the X - Axis
 * @param yAxisReadings These are the readings of the Y - Axis
 * @param yMarkerList This is the list of marker which are present in the Y - Axis
 * @param numOfYMarkers These are teh num of markers in Y-axis
 *
 * @param numOfYMarkers This Is useless when a yMarkerList is passed to the Class constructor
 */
class LinearStringData(
    override val yAxisReadings: List<List<ChartPoint<Float>>>,
    override val xAxisReadings: List<ChartPoint<String>>,
    override var yMarkerList: MutableList<ChartPoint<*>> = mutableListOf(),
    override var numOfYMarkers: Int = 5
) : LinearDataInterface {

    /**
     * These are the num of markers in X-Axis
     */
    override val numOfXMarkers: Int = xAxisReadings.size

    /**
     * Upper Y - Axis Reading or the Maximum Reading of the Graph
     */
    private var yUpperReading: Int = Int.MIN_VALUE

    /**
     * Lower Y - Axis Reading or the Maximum Reading of the Graph
     */
    private var yLowerReading: Int = Int.MAX_VALUE

    /**
     * It is the difference of the Upper and Lower Markers divided by the Markers count
     */
    private var yDividend: Int

    init {

        // Checking if the markers are provided or we need to calculate
        if (yMarkerList.isNotEmpty()) {

            numOfYMarkers = yMarkerList.size

            // Storing the upper Bound and Lower bound of Y Markers
            yUpperReading = yMarkerList.size - 1
            yLowerReading = 0

            // Difference between each Y Markers
            yDividend = 1
        } else {

            // Maximum and minimum value provided is calculated
            val yMax = yAxisReadings.maxOf {
                it.maxOf { point ->
                    point.value
                }
            }
            val yMin = yAxisReadings.minOf {
                it.minOf { point ->
                    point.value
                }
            }

            // Storing the upper Bound and Lower bound of Y Markers
            yUpperReading = if (yMax % (numOfYMarkers - 1) != 0.0f)
                yMax.toInt() + ((numOfYMarkers - 1) - (yMax.toInt() % (numOfYMarkers - 1)))
            else
                yMax.toInt()

            yLowerReading = if (yMin.toInt() % (numOfYMarkers - 1) == 0) {
                yMin.toInt() - (numOfYMarkers - 1)
            } else {
                yMin.toInt() - (yMin.toInt() % (numOfYMarkers - 1))
            }

            // Difference between each Y Markers
            yDividend = (yUpperReading - yLowerReading) / (numOfYMarkers - 1)

            // Calculating the points for Y - Axis markers
            for (index in 0 until numOfYMarkers) {

                // This is the value of the current Y Axis Marker
                val currentYMarker = yUpperReading - (index) * yDividend
                yMarkerList.add(index, ChartPoint(currentYMarker))
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
        val yScale = size.height / numOfYMarkers

        // maximum Width of the Y - Markers
        val yMarkerMaxWidth = calculateYMarkersCoordinates(yScale = yScale)

        // X - Axis Scale
        val xScale = (size.width - yMarkerMaxWidth) / numOfXMarkers

        // This function calculates the Coordinates for the Readings
        calculateReadingsCoordinates(
            xScale = xScale,
            yScale = yScale,
            yMarkerMaxWidth = yMarkerMaxWidth
        )

        // This function calculates the Coordinates for the X - Markers
        calculateXMarkersCoordinates(
            size = size,
            xScale = xScale,
            yMarkerMaxWidth = yMarkerMaxWidth
        )
    }

    /**
     * This function calculates the Y - Axis Markers Coordinates
     *
     * @param yScale This is the scale for the Y - Axis
     */
    private fun DrawScope.calculateYMarkersCoordinates(yScale: Float): Int {

        var yMarkerMaxWidth = 0

        // Calculating all the chart Y - Axis markers in the chart along with their coordinates
        yMarkerList.forEachIndexed { index, point ->

            val bounds = Rect()
            val paint = Paint()

            paint.textSize = 12.sp.toPx()
            paint.textAlign = Paint.Align.LEFT
            paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
            paint.getTextBounds(point.value.toString(), 0, point.value.toString().length, bounds)

            // Current Y Coordinate for the point
            val currentYCoordinate = (yScale * index) + 12f

            // Setting the calculated graph coordinates to the object
            point.setXCoordinate(-24f)
            point.setYCoordinate(currentYCoordinate)

            val width = bounds.width()
            yMarkerMaxWidth = if (yMarkerMaxWidth < width) width else yMarkerMaxWidth
        }
        return yMarkerMaxWidth
    }

    /**
     * This function calculates the Coordinates for the Readings
     *
     * @param xScale This is the scale for the X - Axis
     * @param yScale  This is the scale for the Y - Axis
     * @param yMarkerMaxWidth This is the maximum width of the Y - Markers
     */
    private fun calculateReadingsCoordinates(xScale: Float, yScale: Float, yMarkerMaxWidth: Int) {

        // Taking all the points given and calculating where they will stay in the graph
        yAxisReadings.forEach { pointSet ->

            pointSet.forEachIndexed { index, point ->

                val currentYCoordinate = (yUpperReading - point.value) * yScale / yDividend
                val currentXCoordinate = 48f + (index * xScale) + yMarkerMaxWidth

                // Setting the calculated graph coordinates to the object
                point.setXCoordinate(currentXCoordinate)
                point.setYCoordinate(currentYCoordinate)
            }
        }
    }

    /**
     * This Function calculates the coordinates for the X Markers
     *
     * @param size This is the size of the canvas
     * @param xScale This is the scale for the X - Axis
     * @param yMarkerMaxWidth This is the maximum width of the Y - Markers
     */
    private fun calculateXMarkersCoordinates(size: Size, xScale: Float, yMarkerMaxWidth: Int) {

        // Calculating all the chart X - Axis markers coordinates
        xAxisReadings.forEachIndexed { index, currentMarker ->

            val xCoordinate = (xScale * index) + 48f + yMarkerMaxWidth
            val yCoordinate = size.height

            // Setting the calculated graph coordinates to the object
            currentMarker.setXCoordinate(xCoordinate)
            currentMarker.setYCoordinate(yCoordinate)
        }
    }
}