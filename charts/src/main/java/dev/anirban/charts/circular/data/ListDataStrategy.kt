package dev.anirban.charts.circular.data


/**
 * This class is the implementation of [CircularDataStrategy] class which provides a strategy for
 * calculating the [sweepAngles] of the chart when the data is provided in a list order.
 *
 * For other implementation visit [TargetDataStrategy]
 *
 * @param itemsList This is the List of items to be shown in the chart
 * @param unit This is the unit of the item in [itemsList]
 */
class ListDataStrategy(
    override val itemsList: List<Pair<String, Float>>,
    override val unit: String
) : CircularDataStrategy {


    /**
     * This is the list of sweep angles calculated using [itemsList] and are used to draw plot
     */
    override var sweepAngles: MutableList<Float> = mutableListOf()


    /**
     * This function performs the calculation logic in the class
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