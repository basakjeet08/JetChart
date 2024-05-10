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
data class DataSet<T>(
    val title: String = "",
    val markers: List<Coordinate<T>>
) {


    /**
     * This is the size of the data set
     */
    val size: Int = markers.size

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
        fun <T> createDataSet(title: String, points: List<T>): DataSet<T> =
            DataSet(
                title = title,
                markers = coordinateSetBuilder(points = points)
            )
    }
}
