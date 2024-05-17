package dev.anirban.charts.linear.exceptions

import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.linear.data.LinearDataStrategy

/**
 * This implementation is for handling all the possible exceptions that may cause while running the
 * library and hence custom Exceptions are made for the ease of developers to understand the
 * exceptions and fix them accordingly
 *
 * @property validateDataInput This functions validates the [LinearDataStrategy] is implemented
 * properly and all the data is given properly over there
 * @property validateDecorationInput This function validates the [LinearDecoration] is implemented
 * properly and all the data needed for the Linear Decoration are provided properly
 */
interface LinearExceptionStrategy {

    fun validateDataInput()

    fun validateDecorationInput()

}