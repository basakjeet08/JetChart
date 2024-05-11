package dev.anirban.charts.linear.interfaces

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import dev.anirban.charts.linear.data.LinearDataSet
import dev.anirban.charts.linear.data.*
import dev.anirban.charts.util.Coordinate


/**
 * This is the Data Interface which has to be implemented by the class which makes a new
 * Implementation for the handling of data and calculation of offsets of various observations of
 * the graph data set.
 *
 * Implementations for this interface are :- [LinearStringData] and [LinearEmojiData]
 */
interface LinearDataInterface {


    /**
     * This is the data set of the chart.
     *
     * It uses [LinearDataSet] class object for representing the data set.
     */
    val linearDataSets: List<LinearDataSet>


    /**
     * These are the labels for the X - Axis.
     */
    val xAxisLabels: List<Coordinate<*>>


    /**
     * These are the labels for the Y - Axis.
     */
    var yAxisLabels: MutableList<Coordinate<*>>


    /**
     * These are the count of labels in X-Axis
     */
    val numOfXLabels: Int


    /**
     * These are the num of labels in Y-axis
     */
    val numOfYLabels: Int


    /**
     * This is the function responsible for all the graph related calculations.
     *
     * @param size This is the size of the whole canvas.
     */
    fun DrawScope.doCalculations(size: Size)
}