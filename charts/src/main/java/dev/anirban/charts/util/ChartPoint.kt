package dev.anirban.charts.util

import androidx.compose.ui.geometry.Offset

/**
 * This class is made to indicate each and every point in the graph along with their coordinates for
 * placement
 */
class ChartPoint<T>(
    val value: T
) {

    /**
     * X and Y Coordinate of the points where the value needs to be drawn of plotted
     */
    var xCoordinate: Float = 0f
        private set
    var yCoordinate: Float = 0f
        private set

    /**
     * This function is used to set the X - Coordinate of the object
     */
    fun setXCoordinate(xCoordinate: Float) {
        this.xCoordinate = xCoordinate
    }

    /**
     * This function is used to set the Y - Coordinate of the object
     */
    fun setYCoordinate(yCoordinate: Float) {
        this.yCoordinate = yCoordinate
    }

    /**
     * This function is used to get the Offset of the object
     */
    fun getOffset(): Offset = Offset(xCoordinate, yCoordinate)

    companion object {

        /**
         * This function makes a List of Points Objects
         *
         * This is made to make the creation of Points List easy and less boilerplate code would be
         * written
         */
        fun <T> pointDataBuilder(vararg points: T): List<ChartPoint<T>> {
            return points.map { ChartPoint(it) }
        }

        /**
         * This function makes a List of Points Objects
         *
         * This is made to make the creation of Points List easy and less boilerplate code would be
         * written
         */
        fun <T> pointDataBuilder(points: List<T>): List<ChartPoint<T>> {
            return points.map { ChartPoint(it) }
        }
    }
}
