package dev.anirban.charts.linear.exceptions

import dev.anirban.charts.linear.interfaces.LinearDataInterface

/**
 * This exception is thrown when the X - Axis Readings array [LinearDataInterface.xAxisLabels]
 * contains less text than the list of Y - Axis Readings [LinearDataInterface.linearDataSets]
 */

class LinearDataMismatch(message: String?) : Exception(message)