package dev.anirban.charts.linear.exceptions

import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.data.LinearDataStrategy

/**
 * This exception is thrown when the color array [LinearDecoration.plotPrimaryColor] contains less
 * color than the list of datasets provided at data class [LinearDataStrategy.linearDataSets]
 */

class DecorationMismatch(message: String?) : Exception(message)