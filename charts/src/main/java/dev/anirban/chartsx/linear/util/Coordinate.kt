package dev.anirban.chartsx.linear.util

import androidx.compose.ui.geometry.Offset


/**
 * This class is made to indicate each and every point or coordinate in the graph along with
 * their calculated coordinates in the Canvas Graph.
 */
class Coordinate<T>(private val value: T?) {


    /**
     * This function returns the Value of the Coordinate
     */
    fun getValue(): T? = value


    /**
     * This is the variable which stores the (x,y) for this Coordinate
     */
    var offset: Offset = Offset(0f, 0f)
        private set


    /**
     * This function is used to get the Offset of the object
     */
    fun getOffset(): Offset = offset

    /**
     * This function is used to set the x of the object
     */
    fun setX(x: Float) {
        this.offset.copy(x = x)
    }

    /**
     * This function is used to set y of the object
     */
    fun setY(y: Float) {
        this.offset.copy(y = y)
    }
}
