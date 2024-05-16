package dev.anirban.charts.circular.data

/**
 * This class is the implementation of [CircularDataInterface] class which is responsible for
 * providing the implementation of business login and calculation logic behind the chart
 *
 * @param unit This is the SI Unit text
 *
 * @property sweepAngles This is the list of sweep angles which could be calculated
 */
class CircularDonutTargetData(
    override val itemsList: List<Pair<String, Float>>,
    override val unit: String
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

        val angle = percentage * 360f

        // Adding the angle to the sweepAngle list
        sweepAngles = mutableListOf(angle)
    }
}