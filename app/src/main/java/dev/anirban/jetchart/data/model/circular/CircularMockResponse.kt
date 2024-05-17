package dev.anirban.jetchart.data.model.circular

data class CircularMockResponse(
    val dataSet1: CircularMockObservation,
    val dataSet2: CircularMockObservation,
    val dataSet3: CircularMockTarget,
    val dataSet4: List<CircularMockTarget>
)