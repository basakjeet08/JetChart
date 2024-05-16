package dev.anirban.jetchart.data.model

data class LinearMockResponse(
    val dataSet1: List<LinearMockObservation>,
    val dataSet2: List<LinearMockObservation>,
    val dataSet3: List<LinearMockObservation>,
    val dataSet4: List<LinearMockObservation>,
    val dataSet5: List<LinearMockObservation>,
    val dataSet6: List<LinearMockObservation>,
    val dataSet7: List<LinearMockObservation>,
    val dataSet8: List<LinearMockObservation>,
    val dataSet9: List<LinearMockObservation>,
    val xAxisLabels: List<String>,
    val yAxisLabels: MutableList<String>
)