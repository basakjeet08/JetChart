package dev.anirban.charts.circular.exceptions

import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.CircularChartStrategy


/**
 * This Class provides the interface for the Exception handling of the [CircularChartStrategy]
 *
 * @property validateDecoration This checks for exceptions from [CircularDecoration] class
 * @property validateAll This calls all the other functions for checking their respective exceptions
 */
interface CircularExceptionStrategy {

    fun validateDecoration()

    fun validateAll()
}