package dev.anirban.charts.linear.exceptions

/**
 * This exception will be thrown when there will be a type mismatch between the data class object and
 * the label class object which is not supported
 */
class LinearChartTypeMismatch(message: String?) : Exception(message)