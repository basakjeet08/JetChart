package dev.anirban.chartsx.linear.util


/**
 * This is the blueprint of an dataset of the Chart we are building.
 *
 * It contains both the title to which the data belong and the coordinates of each of the point
 *
 * @param title This is the title of the Data Set
 * @param markers This is the list of coordinates or the marker set.
 */
class CoordinateSet<T>(
    private var title: String? = null,
    private var markers: List<Coordinate<T>> = listOf()
) {

    companion object {


        /**
         * This function makes a dataset for the Graphs.
         *
         * This is made to make the creation of Points List easy and less boilerplate code would be
         * written
         */
        fun <T> coordinateSet(title: String? = null, vararg points: T): CoordinateSet<T> =
            CoordinateSet(
                title = title,
                markers = points.map { Coordinate(it) }
            )


        /**
         * This function makes a List of coordinate Objects
         *
         * This is made to make the creation of Points List easy and less boilerplate code would be
         * written
         */
        fun <T> coordinateSet(title: String? = null, points: List<T>): CoordinateSet<T> =
            CoordinateSet(
                title = title,
                markers = points.map { Coordinate(it) }
            )
    }
}