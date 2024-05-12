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
        val dataSet4 = listOf(generateLinearMockObservation(highRange = 4))

        return LinearMockResponse(
            dataSet1 = dataSet1,
            dataSet2 = dataSet2,
            dataSet3 = dataSet3,
            dataSet4 = dataSet4
        )
    }


    private fun generateLinearMockObservation(
        observations: Int = 7,
        highRange: Int = 50
    ): LinearMockObservation {
        val observationData = List(observations) { (1..highRange).random().toFloat() }
        val title = titles[observationCount++ % titles.size]

        return LinearMockObservation(
            title = title,
            dataSet = observationData
        )
    }
}