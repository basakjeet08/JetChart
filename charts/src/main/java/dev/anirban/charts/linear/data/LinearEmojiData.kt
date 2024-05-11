package dev.anirban.charts.linear.data

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import dev.anirban.charts.linear.interfaces.LinearDataInterface
import dev.anirban.charts.util.Coordinate


/**
 * This class implements the [LinearDataInterface] and stores the data necessary for a linear chart.
 * For other implementations check [LinearStringData].
 *
 * @param linearDataSets This is the data set of the chart.
 * @param xAxisLabels These are the labels for the X - Axis.
 * @param yAxisLabels These are the labels for the Y - Axis.
 * @param dimension This is the dimension of the label drawable or observations.
 */
class LinearEmojiData(
    override val linearDataSets: List<LinearDataSet>,
    override val xAxisLabels: List<Coordinate<String>>,
    override var yAxisLabels: MutableList<Coordinate<*>> = mutableListOf(),
    val dimension: Int = 50
) : LinearDataInterface {


    /**
     * These are the count of labels in X-Axis and Y - Axis.
     */
    override val numOfXLabels: Int = xAxisLabels.size
    override var numOfYLabels: Int = yAxisLabels.size


    /**
     * The maximum or peak Y Label of the Graph.
     */
    private var maxYLabel: Int = yAxisLabels.size - 1


    /**
     * This is the difference between each Y label and its subsequent label.
     */
    private var yLabelDifference: Int = 1


    /**
     * This is the offset of the X Axis from the initial starting point.
     */
    private val xAxisOffset: Float = 48f


    /**
     * These are the X and Y Scales for the graph.
     */
    private var xScale: Float = 0f
    private var yScale: Float = 0f


    /**
     * This is the function responsible for all the graph related calculations.
     *
     * @param size This is the size of the whole canvas.
     */
    override fun DrawScope.doCalculations(size: Size) {

        // Scale of Y Axis of the graph
        yScale = size.height / numOfYLabels

        // Maximum width of the Y labels. Needs y Scale to be calculated beforehand.
        val yMarkerMaxWidth = calculateYLabelCoordinates()

        // X - Axis Scale
        xScale = (size.width - yMarkerMaxWidth) / numOfXLabels

        // This function calculates the offset of the markers/observation in the graph
        calculateMarkersCoordinates(yLabelMaxWidth = yMarkerMaxWidth)

        // This function calculates the offset for the X labels in the graph
        calculateXLabelsCoordinates(size = size, yLabelsMaxWidth = yMarkerMaxWidth)
    }


    /**
     * This function calculates the Y axis labels offsets.
     */
    private fun calculateYLabelCoordinates(): Int {

        var maxDimension = 0

        // Calculating all the Y axis labels in the chart along with their offset.
        yAxisLabels.forEachIndexed { index, point ->

            with(point) {
                value as BitmapDrawable

                // Current Y offset for the point.
                val currentYOffset = (yScale * index) - (dimension.toFloat() / 2f)

                val resizedBitmap =
                    Bitmap.createScaledBitmap(value.bitmap, dimension, dimension, true)

                if (resizedBitmap.width > maxDimension)
                    maxDimension = resizedBitmap.width

                // Setting the calculated graph offset of the object.
                setOffset(x = -24f, y = currentYOffset)
            }
        }
        return maxDimension
    }


    /**
     * This function calculates the offset for the markers or observation or data set.
     *
     * @param yLabelMaxWidth This is the maximum width of the Y labels.
     */
    private fun calculateMarkersCoordinates(yLabelMaxWidth: Int) {

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
    private fun calculateXLabelsCoordinates(size: Size, yLabelsMaxWidth: Int) {

        // Calculating all the chart X axis labels offset
        xAxisLabels.forEachIndexed { index, currentMarker ->

            val xCoordinate = (xScale * index) + xAxisOffset + yLabelsMaxWidth
            val yCoordinate = size.height

            // Setting the calculated graph offsets to the object
            currentMarker.setOffset(x = xCoordinate, y = yCoordinate)
        }
    }
}