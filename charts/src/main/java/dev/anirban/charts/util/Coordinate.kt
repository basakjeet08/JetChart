package dev.anirban.charts.util

import androidx.compose.runtime.Immutable
import androidx.compose.ui.geometry.Offset


/**
 * This class is made to indicate each and every point or coordinate in the graph along with
 * their calculated coordinates in the Canvas Graph.
 *
 * @param T The type of the [value] that is being plotted
 * @param value This is the value of the object that is being plotted (We calculate its
 * coordinates according to this value)
 */
@Immutable
data class Coordinate<T>(val value: T) {


    /**
     * This is the graphical x Coordinate of the object
     */
    var x: Float = 0f
        private set


    /**
     * This is the graphical y Coordinate of the object
     */
    var y: Float = 0f
        private set


    /**
     * This function sets the graphical offset of the object
     *
     * @param x The graphical x Coordinate of the object
     * @param y The graphical y Coordinate of the object
     */
    fun setOffset(x: Float, y: Float) {
        this.x = x
        this.y = y
    }


    /**
     * This function sets the graphical offset of the object
     *
     * @param offset The graphical offset of the object
     */
    fun setOffset(offset: Offset) {
        this.x = offset.x
        this.y = offset.y
    }


    /**
     * This is the graphical offset of the object combining the x and y coordinates
     */
    fun offset(): Offset = Offset(x, y)


    companion object {


        /**
         * This function makes a coordinate set for the Graphs.
         *
         * This is made to make the creation of Points List easy and less boilerplate code would be
         * written
         *
         * @param points The points that are being plotted in the graph
         */
        fun <T> coordinateSetBuilder(vararg points: T): List<Coordinate<T>> =
            points.map(::Coordinate)


        /**
         * This function makes a coordinate set for the Graphs.
         *
         * This is made to make the creation of Points List easy and less boilerplate code would be
         * written
         *
         * @param points The points that are being plotted in the graph
         */
        fun <T> coordinateSetBuilder(points: List<T>): List<Coordinate<T>> =
            points.map(::Coordinate)


        /**
         * This function makes a coordinate set for the Graphs.
         *
         * This is made to make the creation of Points List easy and less boilerplate code would be
         * written
         *
         * This is an extension function of the [List] and can directly convert a [List] object into
         * a CoordinateSet or a List of Coordinates
         */
        fun <T> List<T>.toCoordinateSet(): List<Coordinate<T>> = this.map(::Coordinate)
    }
}
