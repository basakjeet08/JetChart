package dev.anirban.charts.circular.exceptions

import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.data.CircularDataInterface

/**
 * This class is used to throw an exception when the [CircularDecoration.colorList] size is lesser
 * than the [CircularDataInterface.itemsList] size
 */
class CircularDecorationMismatch(message: String?) : Exception(message)