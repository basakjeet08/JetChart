package dev.anirban.charts.linear.data

import dev.anirban.charts.linear.data.Coordinate.Companion.toCoordinateSet


/**
 * This is the data structure for the chart. This data structure is designed to represent an data
 * set or observations along with its category/type/title .
 *
 * @param title This is the title/type/category of the data set
 * @param markers This is the list of values or readings which are observed.
 */
data class LinearDataSet(
    val title: String = "",
    val markers: List<Coordinate<Float>>
) {


    /**
     * This is the size of the data set / observations / readings
     *
     * For any data set the size is always the size of [LinearDataSet.markers]
     */
    val size: Int = markers.size


    /**
     * This is the maximum value or the peak of the given data set in [LinearDataSet.markers]
     */
    val max: Float = markers.maxOf { it.value }


    /**
     * This is the minimum value or the lowest point of the given data set in [LinearDataSet.markers]
     */
    val min: Float = markers.minOf { it.value }


    /**
     * This function is used to iterate through the [LinearDataSet.markers], perform an action on
     * and access each element inside the [LinearDataSet.markers] list.
     * [List.forEach] is called internally.
     *
     * @param action This is the action to be performed on each element.
     */
    fun forEach(action: (Coordinate<Float>) -> Unit) {
        markers.forEach(action)
    }


    /**
     * This function is used to iterate through the [LinearDataSet.markers], perform an action on
     * and access each element inside the [LinearDataSet.markers] list along with its index.
     * [List.forEachIndexed] is called internally.
     *
     * @param action This is the action to be performed on each element.
     */
    fun forEachIndexed(action: (Int, Coordinate<Float>) -> Unit) {
        markers.forEachIndexed(action)
    }


    companion object {


        /**
         * Creates a data set or observation list for the graphs which can be used by the library.
         * It generally takes a given [List] data type and converts it to a [LinearDataSet] object.
         *
         * This function simplifies the creation of data set and reduces boilerplate code.It takes
         * the title and a [List] of observations as input and returns a data set object.
         *
         * @param title This is the title/type/category of the data set.
         * @param markers This is the [List] of observations or the input data set.
         */
        fun createDataSet(title: String, markers: List<Float>): LinearDataSet =
            LinearDataSet(
                title = title,
                markers = markers.toCoordinateSet()
            )
    }
}
