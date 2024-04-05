package dev.anirban.charts.linear.exceptions

import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.interfaces.LinearColorConventionInterface

/**
 * This exception is thrown when the Text List array [LinearColorConventionInterface.textList]
 * contains more text than the list of primary color at decoration class
 * [LinearDecoration.plotPrimaryColor]
 */
class LinearColorConventionMismatch(message: String?) : Exception(message)
