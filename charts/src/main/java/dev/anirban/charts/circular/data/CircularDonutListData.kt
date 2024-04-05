package dev.anirban.charts.circular.data

import dev.anirban.charts.circular.interfaces.CircularDataInterface

/**
 * This class is the implementation of [CircularDataInterface] class which is responsible for
 * providing the implementation of business login and calculation logic behind the chart
 *
 * @param itemsList This is the List of items to be shown in the chart
 * @param siUnit This is the SI Unit text
 * @param cgsUnit This is the CGS Unit text
 * @param conversionRate This is the conversion rate according to which the CGS values can be
 * transformed into SI Unit
 *
 * @property sweepAngles This is the list of sweep angles which could be calculated
 */
class CircularDonutListData(
    override val itemsList: List<Pair<String, Float>>,
    override val siUnit: String,
    override val cgsUnit: String,
    override val conversionRate: (Float) -> Float
) : CircularDataInterface {

    override var sweepAngles: MutableList<Float> = mutableListOf()

    /**
     * This function calculates the sweep Angles
     */
    override fun doCalculations() {

        // Extracting the Floating values from the given list
        val dataList = itemsList.map { it.second }

        // Stores the sum of all the items in the list
        val sum = dataList.sum()

        /**
         * some value is subtracted because according to the UI there shall be some free space
         * between each graph.
         *
         * Free Space = Some Angles shall be subtracted so that
         *
         * We are taking a 4f minus between each and every Floating Data
         */
        sweepAngles = dataList.map { (it / sum) * (360f - (dataList.size * 4f)) }.toMutableList()

    }
}