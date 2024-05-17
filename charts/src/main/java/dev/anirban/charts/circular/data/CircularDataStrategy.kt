package dev.anirban.charts.circular.data


/**
 * A strategy prototype for calculating data and angles for a circular chart plot.
 *
 * Implementations for this interface is :- [ListDataStrategy],[TargetDataStrategy]
 */
interface CircularDataStrategy {


    /**
     * This is the list of items which are shown in the readings
     */
    val itemsList: List<Pair<String, Float>>


    /**
     * This is the list of sweep angles calculated using [itemsList] and are used to draw plot
     */
    var sweepAngles: MutableList<Float>


    /**
     * This is the unit of the item in [itemsList]
     */
    val unit: String


    /**
     * This function performs the calculation logic in the class
     */
    fun doCalculations()
}