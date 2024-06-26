package dev.anirban.charts.linear.exceptions

import dev.anirban.charts.linear.data.LinearDataStrategy

/**
 * This exception is thrown when the x axis labels [LinearDataStrategy.xAxisLabels]
 * is less than the list of observations or the data set [LinearDataStrategy.linearDataSets]
 */

class DataMismatch(message: String?) : Exception(message)