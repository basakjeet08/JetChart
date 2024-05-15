package dev.anirban.charts.linear.exceptions

import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.interfaces.LinearDataStrategy

/**
 * This exception is thrown when the color array [LinearDecoration.plotPrimaryColor] and
 * [LinearDecoration.plotSecondaryColor] contains less color than the list of datasets provided at
 * data class [LinearDataStrategy.linearDataSets]
 */

class DecorationMismatchStrategy(message: String?) : Exception(message)