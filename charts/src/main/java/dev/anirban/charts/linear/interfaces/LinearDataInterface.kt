package dev.anirban.charts.linear.interfaces

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import dev.anirban.charts.linear.data.DataSet
import dev.anirban.charts.linear.data.LinearStringData
import dev.anirban.charts.util.Coordinate

/**
 * This is the Data Interface which has to be implemented by the class which makes a new
 * Implementation for the handling of data and calculations in the graph
 *
 * Implementations for this interface are :- [LinearStringData]
 */
interface LinearDataInterface {

    /**
     * These are the readings of the Y - Axis
     */
    val dataSets: List<DataSet<Float>>


    /**
     * These are the readings of the X - Axis
     */
    val xAxisReadings: List<Coordinate<*>>


    /**
     * These are the markers needed in X Axis
     */
    val numOfXMarkers: Int


    /**
     * These are teh num of markers in Y-axis
     */
    val numOfYMarkers: Int


    /**
     * List of all the markers in the Y - Axis
     */
    var yMarkerList: MutableList<Coordinate<*>>


    /**
     * THis is the function which contains most of the calculation logic of the graph
     */
    fun DrawScope.doCalculations(size: Size)

}