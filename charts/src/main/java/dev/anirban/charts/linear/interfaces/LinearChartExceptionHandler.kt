package dev.anirban.charts.linear.interfaces

import dev.anirban.charts.linear.decoration.LinearDecoration

/**
 * This implementation is for handling all the possible exceptions that may cause while running the
 * library and hence custom Exceptions are made for the ease of developers to understand the
 * exceptions and fix them accordingly
 *
 * @property validateDataInput This functions validates the [LinearDataInterface] is implemented
 * properly and all the data is given properly over there
 * @property validateDecorationInput This function validates the [LinearDecoration] is implemented
 * properly and all the data needed for the Linear Decoration are provided properly
 * @property validateTypeMismatch This function validates if the label and the data passed are
 * supported or not so it can give a meaningful result to the developer
 */
interface LinearChartExceptionHandler {

    fun validateDataInput()

    fun validateDecorationInput()

    fun validateTypeMismatch()
}