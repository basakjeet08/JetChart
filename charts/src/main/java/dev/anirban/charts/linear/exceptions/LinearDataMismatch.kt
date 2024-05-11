package dev.anirban.charts.linear.exceptions

import dev.anirban.charts.linear.interfaces.LinearDataInterface

/**
 * This exception is thrown when the x axis labels [LinearDataInterface.xAxisLabels]
 * is less than the list of observations or the data set [LinearDataInterface.linearDataSets]
 */

class LinearDataMismatch(message: String?) : Exception(message)