package dev.anirban.charts.circular.exceptions

import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.data.CircularDataStrategy

/**
 * This class is used to throw an exception when the [CircularDecoration.colorList] size is lesser
 * than the [CircularDataStrategy.itemsList] size
 */
class DecorationMismatch(message: String?) : Exception(message)