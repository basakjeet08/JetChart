package dev.anirban.jetchart.data.model.linear

import dev.anirban.charts.linear.data.LinearDataSet


data class LinearMockObservation(
    val title: String,
    val dataSet: List<Float>
) {


    /**
     * This function converts the [LinearMockObservation] into an [LinearDataSet] object
     */
    fun toLinearDataSet(): LinearDataSet {
        return LinearDataSet.createDataSet(
            title = title,
            markers = dataSet
        )
    }
}

