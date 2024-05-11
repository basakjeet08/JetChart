package dev.anirban.charts.linear.data

import dev.anirban.charts.util.Coordinate
import dev.anirban.charts.util.Coordinate.Companion.coordinateSetBuilder


/**
 * This is the blueprint of an dataset of the Chart we are building.
 *
 * It contains both the title to which the data belong and the coordinates of each of the point
 *
 * @param title This is the title of the Data Set
 * @param markers This is the list of coordinates or the data set.
 */
data class DataSet(
    val title: String = "",
    val markers: List<Coordinate<Float>>
) {


    /**
     * This is the size of the data set
     */
    val size: Int = markers.size


    /**
     * This is the maximum value of the data set
     */
    val max: Float = markers.maxOf { it.value }


    /**
     * This is the minimum value of the data set
     */
    val min: Float = markers.minOf { it.value }


    /**
     * This function is used to iterate through the [DataSet.markers] and perform an action on
     * each element.
     *
     * @param action This is the Action to be performed on each element.
     */
    fun forEach(action: (Coordinate<Float>) -> Unit) {
        markers.forEach(action)
    }


    /**
     * This function is used to iterate through the [DataSet.markers] and perform an action on
     * each element.
     *
     * @param action This is the Action to be performed on each element.
     */
    fun forEachIndexed(action: (Int, Coordinate<Float>) -> Unit) {
        markers.forEachIndexed(action)
    }


    companion object {


        /**
         * Creates a DataSet for the Graphs.
         *
         * This function simplifies the creation of Points List and reduces boilerplate code.
         * It takes a title and a list of points as input and returns a DataSet object.
         *
         * @param title This is the title of the Data Set
         * @param points This is the list of coordinates or the data set.
         */
        fun createDataSet(title: String, points: List<Float>): DataSet =
            DataSet(
                title = title,
                markers = coordinateSetBuilder(points = points)
            )
    }
}
