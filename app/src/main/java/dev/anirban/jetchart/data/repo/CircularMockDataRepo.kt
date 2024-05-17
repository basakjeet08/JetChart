package dev.anirban.jetchart.data.repo

import dev.anirban.jetchart.data.model.circular.CircularMockObservation
import dev.anirban.jetchart.data.model.circular.CircularMockResponse
import dev.anirban.jetchart.data.model.circular.CircularMockTarget

object CircularMockDataRepo {

    private val titles: List<String> = listOf("Amazon", "Flipkart", "Facebook", "Netflix", "Google")
    private var observationCount = 0


    fun generateMockResponse(): CircularMockResponse = CircularMockResponse(
        dataSet1 = generateMockObservation(),
        dataSet2 = generateMockObservation(),
        dataSet3 = generateMockTargetData(),
        dataSet4 = weeklyMockData()
    )

    private fun weeklyMockData(): List<CircularMockTarget> = List(7) {
        val target = (0..1000).random().toFloat()
        val achieved = (0..1000).random().toFloat()

        CircularMockTarget(
            target = target,
            achieved = achieved,
            unit = "user"
        )
    }

    private fun generateMockTargetData(): CircularMockTarget = CircularMockTarget(
        target = (0..1000).random().toFloat(),
        achieved = (0..1000).random().toFloat(),
        unit = "user"
    )

    private fun generateMockObservation(
        observations: Int = 5,
        highRange: Int = 1000
    ): CircularMockObservation {

        val observationData = List(observations) {
            val title = titles[observationCount++ % titles.size]
            val value = (0..highRange).random().toFloat()
            Pair(title, value)
        }

        return CircularMockObservation(
            itemList = observationData,
            unit = "user"
        )
    }
}