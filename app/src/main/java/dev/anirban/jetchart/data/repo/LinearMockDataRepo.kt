package dev.anirban.jetchart.data.repo

import dev.anirban.jetchart.data.model.LinearMockObservation
import dev.anirban.jetchart.data.model.LinearMockResponse


/**
 * This dummy util object contains the utility functions to generate mock data on behalf of the
 * server.
 */
object LinearMockDataRepo {

    private val titles: List<String> = listOf("Amazon", "Flipkart", "Facebook", "Netflix")
    private var observationCount = 0
    private val xAxisLabel = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul")
    private val yAxisLabel = mutableListOf("Excellent", "Good", "Average", "Bad", "Worst")

    fun generateLinearMockDataResponse(): LinearMockResponse {

        val dataSet1 = listOf(generateLinearMockObservation())
        val dataSet2 = listOf(
            dataSet1[0],
            generateLinearMockObservation()
        )
        val dataSet3 = listOf(
            dataSet2[0],
            dataSet2[1],
            generateLinearMockObservation()
        )
        val dataSet4 = listOf(
            dataSet3[0],
            dataSet3[1],
            dataSet3[2],
            generateLinearMockObservation()
        )

        val dataSet5 = listOf(generateLinearMockObservation(highRange = 4))

        val dataSet6 = listOf(generateLinearMockObservation(highRange = 4))
        val dataSet7 = listOf(
            dataSet6[0],
            generateLinearMockObservation(highRange = 4)
        )
        val dataSet8 = listOf(
            dataSet7[0],
            dataSet7[1],
            generateLinearMockObservation(highRange = 4)
        )
        val dataSet9 = listOf(
            dataSet8[0],
            dataSet8[1],
            dataSet8[2],
            generateLinearMockObservation(highRange = 4)
        )

        return LinearMockResponse(
            dataSet1 = dataSet1,
            dataSet2 = dataSet2,
            dataSet3 = dataSet3,
            dataSet4 = dataSet4,
            dataSet5 = dataSet5,
            dataSet6 = dataSet6,
            dataSet7 = dataSet7,
            dataSet8 = dataSet8,
            dataSet9 = dataSet9,
            xAxisLabels = xAxisLabel,
            yAxisLabels = yAxisLabel
        )
    }


    private fun generateLinearMockObservation(
        observations: Int = 7,
        highRange: Int = 50
    ): LinearMockObservation {
        val observationData = List(observations) { (0..highRange).random().toFloat() }
        val title = titles[observationCount++ % titles.size]

        return LinearMockObservation(
            title = title,
            dataSet = observationData
        )
    }
}