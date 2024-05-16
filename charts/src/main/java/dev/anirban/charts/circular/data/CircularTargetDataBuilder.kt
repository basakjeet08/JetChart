package dev.anirban.charts.circular.data


/**
 * This data class helps building the target and achieved data type by being a wrapper class and
 * letting us formulate data to the respective class after turing the data into List
 *
 * @param target This is the target variable
 * @param achieved This variable denotes the amount achieved
 * @param unit This is the SI Unit text
 */
class CircularTargetDataBuilder(
    private var target: Float,
    private var achieved: Float,
    private var unit: String,
) {

    /**
     * This function converts the [CircularTargetDataBuilder] class object into
     * [CircularDonutTargetData] object
     */
    fun toCircularDonutTargetData() = CircularDonutTargetData(
        itemsList = listOf(
            Pair("Target", target),
            Pair("Achieved", achieved)
        ),
        unit = unit
    )
}