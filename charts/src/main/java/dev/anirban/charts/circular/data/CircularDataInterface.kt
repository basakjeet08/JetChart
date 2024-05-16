package dev.anirban.charts.circular.data


/**
 * This implementation needs to be implemented by all the classes which provide an implementation to
 * handle data
 *
 * Implementations for this interface is :- [CircularDonutListData],[CircularDonutTargetData],
 * [CircularTargetDataBuilder]
 *
 * @property itemsList This is the list of items which are shown in the readings
 * @property sweepAngles This is the list of sweep angles which would be used to draw the readings
 * in the canvas
 *
 * @property unit This is the SI Unit text
 *
 * @property doCalculations This function performs the calculation login in the class
 */
interface CircularDataInterface {

    val itemsList: List<Pair<String, Float>>
    var sweepAngles: MutableList<Float>

    val unit: String

    fun doCalculations()
}