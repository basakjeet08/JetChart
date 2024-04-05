package dev.anirban.charts.circular.interfaces

import dev.anirban.charts.circular.data.*

/**
 * This implementation needs to be implemented by all the classes which provide an implementation to
 * handle data
 *
 * Implementations for this interface is :- [CircularDonutListData],[CircularDonutTargetData],
 * [CircularRingTargetData],[CircularTargetDataBuilder]
 *
 * @property itemsList This is the list of items which are shown in the readings
 * @property sweepAngles This is the list of sweep angles which would be used to draw the readings
 * in the canvas
 *
 * @property siUnit This is the SI Unit text
 * @property cgsUnit This is the CGS Unit text
 * @property conversionRate This is the conversion rate according to which the CGS values can be
 * transformed into SI Unit
 *
 * @property doCalculations This function performs the calculation login in the class
 */
interface CircularDataInterface {

    val itemsList: List<Pair<String, Float>>
    var sweepAngles: MutableList<Float>

    val siUnit: String
    val cgsUnit: String

    val conversionRate: (Float) -> Float

    fun doCalculations()
}