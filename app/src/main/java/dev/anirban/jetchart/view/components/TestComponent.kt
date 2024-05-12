package dev.anirban.jetchart.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.dp
import dev.anirban.charts.linear.axis.LinearStringAxis
import dev.anirban.charts.linear.data.LinearDataSet
import dev.anirban.charts.linear.decoration.LinearDecoration
import dev.anirban.charts.util.Coordinate
import dev.anirban.jetchart.data.model.LinearMockResponse

@Composable
fun TestComponent(mockLinearData: LinearMockResponse) {

    // Example Data Sets
    val xReadingMarker by remember {
        mutableStateOf(Coordinate.coordinateSetBuilder("A", "B", "C", "D", "E", "F", "G"))
    }
    // Data Set Samples and various ways to create them
    val linearDataSet1 = mockLinearData.dataSet1.map {
        LinearDataSet.createDataSet(
            title = it.title,
            markers = it.dataSet
        )
    }

    val linearAxis = LinearStringAxis(
        xAxisLabels = xReadingMarker,
        yAxisLabels = Coordinate.coordinateSetBuilder(
            "Very High",
            "High",
            "Moderate",
            "Average",
            "Bad"
        ).toMutableList()
    )

    val decoration = LinearDecoration.lineDecorationColors()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 24.dp, bottom = 18.dp, end = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .drawBehind {

                    linearAxis.apply {
                        startCalculation(size, linearDataSet1)

                        drawXLabels(decoration)
                        drawYLabels(decoration)
                    }
                }
        )
    }
}