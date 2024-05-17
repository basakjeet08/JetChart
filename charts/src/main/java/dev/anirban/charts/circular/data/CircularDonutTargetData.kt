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
    target: Float,
    achieved: Float,
    override val unit: String
) : CircularDataInterface {

    override val itemsList: List<Pair<String, Float>> = listOf(
        Pair("Achieved", achieved),
        Pair("Target", target)
    )
    override var sweepAngles: MutableList<Float> = mutableListOf()

    var percentage = achieved / target
        private set

    /**
     * This function calculates the sweep Angles
     */
    override fun doCalculations() {

        // Checking if the percentage is above 100 % or not
        if (percentage.isNaN())
            percentage = 0f

        if (percentage > 1)
            percentage = 1f

        val angle = percentage * 360f

        // Adding the angle to the sweepAngle list
        sweepAngles = mutableListOf(angle)
    }
}