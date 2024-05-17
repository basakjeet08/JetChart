package dev.anirban.charts.circular.data


/**
 * This class is the implementation of [CircularDataStrategy] class which provides a strategy for
 * calculating [sweepAngles] for a circular chart when the target and achieved values are provided.
 *
 * For other implementations visit [ListDataStrategy]
 *
 * @param target This is the target to be achieved.
 * @param achieved This is the value achieved
 * @param unit This is the unit of the item in [itemsList]
 */
class TargetDataStrategy(
    target: Float,
    achieved: Float,
    override val unit: String
) : CircularDataStrategy {


    /**
     * This is the list of items which are shown in the readings
     */
    override val itemsList: List<Pair<String, Float>> = listOf(
        Pair("Achieved", achieved),
        Pair("Target", target)
    )


    /**
     * This is the list of sweep angles calculated using [itemsList] and are used to draw plot
     */
    override var sweepAngles: MutableList<Float> = mutableListOf()


    /**
     * This is the achieved percentage.
     */
    private var percentage = achieved / target


    /**
     * This function performs the calculation logic in the class
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