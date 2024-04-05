package dev.anirban.charts.linear.exceptions

import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.interfaces.LinearDataInterface

/**
 * This exception is thrown when the color array [LinearDecoration.plotPrimaryColor] and
 * [LinearDecoration.plotSecondaryColor] contains less color than the list of Y - Axis Readings at
 * data class [LinearDataInterface.yAxisReadings]
 */

class LinearDecorationMismatch(message: String?) : Exception(message)