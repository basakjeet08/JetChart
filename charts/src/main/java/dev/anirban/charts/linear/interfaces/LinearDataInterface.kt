package dev.anirban.charts.linear.interfaces

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import dev.anirban.charts.linear.data.LinearDataSet
import dev.anirban.charts.linear.data.*
import dev.anirban.charts.util.Coordinate


/**
 * This is the Data Interface which has to be implemented by the class which makes a new
 * Implementation for the handling of data and calculations in the graph
 *
 * Implementations for this interface are :- [LinearStringData] and [LinearEmojiData]
 */
interface LinearDataInterface {


    /**
     * These are the Data Sets for the Chart.
     *
     * It uses [LinearDataSet] class object for representing the data set.
     */
    val linearDataSets: List<LinearDataSet>


    /**
     * These are the labels of the X - Axis
     */
    val xAxisLabels: List<Coordinate<*>>


    /**
     * These are the num of labels in X-Axis
     */
    val numOfXLabels: Int


    /**
     * These are the num of labels in Y-axis
     */
    val numOfYLabels: Int


    /**
     * These are the labels of the X - Axis
     */
    var yAxisLabels: MutableList<Coordinate<*>>


    /**
     * This is the function which is responsible for the calculations of all the graph related stuff
     *
     * @param size This is the size of the whole canvas which also haves the componentSize in it
     */
    fun DrawScope.doCalculations(size: Size)
}