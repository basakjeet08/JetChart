package dev.anirban.charts.circular.data

import dev.anirban.charts.circular.interfaces.CircularDataInterface


/**
 * This class is the implementation of [CircularDataInterface] class which is responsible for
 * providing the implementation of business login and calculation logic behind the chart
 *
 * @param siUnit This is the SI Unit text
 * @param cgsUnit This is the CGS Unit text
 * @param conversionRate This is the conversion rate according to which the CGS values can be
 * transformed into SI Unit
 *
 * @property sweepAngles This is the list of sweep angles which could be calculated
 *
 */

class CircularRingTargetData(
    override val itemsList: List<Pair<String, Float>>,
    override val siUnit: String,
    override val cgsUnit: String,
    override val conversionRate: (Float) -> Float,
) : CircularDataInterface {

    override var sweepAngles: MutableList<Float> = mutableListOf()

    /**
     * This function calculates the sweep Angles
     */
    override fun doCalculations() {

        // Percentage of the target achieved by the user
        var percentage = itemsList[1].second / itemsList[0].second

        // Checking if the percentage is above 100 % or not
        if (percentage > 1)
            percentage = 1f

        val angle = percentage * 300f

        // Adding the angle to the sweepAngle list
        sweepAngles = mutableListOf(angle)
    }

}