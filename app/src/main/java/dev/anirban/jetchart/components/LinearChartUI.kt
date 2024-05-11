package dev.anirban.jetchart.components


import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import dev.anirban.charts.linear.LinearChart
import dev.anirban.charts.linear.data.LinearDataSet
import dev.anirban.charts.linear.data.LinearStringData
import dev.anirban.charts.linear.plots.LinearGradientPlot
import dev.anirban.charts.util.Coordinate
import dev.anirban.jetchart.screens.CustomCard


@Composable
fun LinearChartUI(onReload: () -> Unit) {

    // Example Data Sets
    val xReadingMarker by remember {
        mutableStateOf(Coordinate.coordinateSetBuilder("A", "B", "C", "D", "E", "F", "G"))
    }

    val linearDataSet1 = listOf(
        LinearDataSet.createDataSet(
            title = "Netflix",
            points = listOf(6f, 5f, 4f, 6f, 7.5f, 7f, 6f)
        )
    )

    val linearDataSet2 = listOf(
        linearDataSet1[0],
        LinearDataSet.createDataSet(
            title = "Amazon",
            points = listOf(3f, 6f, 8f, 2f, 3.5f, 3f, 4f)
        )
    )

    val linearDataSet3 = listOf(
        linearDataSet2[0],
        linearDataSet2[1],
        LinearDataSet.createDataSet(
            title = "Google",
            points = listOf(1f, 8f, 4f, 3f, 5.9f, 2.9f, 4.7f)
        )
    )

    val linearDataSet4 = listOf(
        LinearDataSet.createDataSet(
            title = "Facebook",
            points = listOf(4f, 0f, 1.7f, 1.9f, 2f, 4f)
        )
    )

    Button(onClick = onReload) {
        Text(text = "Reload DataSet")
    }


    // Design Pattern Single Line Chart
    CustomCard(title = "Single Line Chart") {

        LinearChart.LineChart(
            linearData = LinearStringData(
                linearDataSets = linearDataSet1,
                xAxisLabels = xReadingMarker
            )
        )
    }

    // Design Pattern Double Line Chart
    CustomCard(title = "Double Line Chart") {

        LinearChart.LineChart(
            linearData = LinearStringData(
                linearDataSets = linearDataSet2,
                xAxisLabels = xReadingMarker
            )
        )
    }

    // Design Pattern Triple Line Chart
    CustomCard(title = "Multiple Line Chart") {

        LinearChart.LineChart(
            linearData = LinearStringData(
                linearDataSets = linearDataSet3,
                xAxisLabels = xReadingMarker
            )
        )
    }

    // Design Pattern String Marker Line Chart
    CustomCard(title = "String Marker Chart") {
        LinearChart.LineChart(
            linearData = LinearStringData(
                linearDataSets = linearDataSet4,
                xAxisLabels = xReadingMarker,
                yAxisLabels = Coordinate.coordinateSetBuilder(
                    "Very High",
                    "High",
                    "Moderate",
                    "Average",
                    "Bad"
                ).toMutableList()
            )
        )
    }

    // Design Pattern String Marker Line Chart
//        CustomCard(
//            title = "Emoji Marker Chart"
//        ) {
//            LinearChart.EmojiLineChart(
//                linearData = LinearEmojiData(
//                    yAxisReadings = dataSet,
//                    xAxisReadings = xReadingMarker,
//                    yMarkerList = ChartPoint.pointDataBuilder(
//                        getDrawable(
//                            LocalContext.current,
//                            R.drawable.emoji_furious
//                        ) as BitmapDrawable,
//                        getDrawable(LocalContext.current, R.drawable.emoji_angry) as BitmapDrawable,
//                        getDrawable(LocalContext.current, R.drawable.emoji_sad) as BitmapDrawable,
//                        getDrawable(
//                            LocalContext.current,
//                            R.drawable.emoji_depressed
//                        ) as BitmapDrawable,
//                        getDrawable(
//                            LocalContext.current,
//                            R.drawable.emoji_confused
//                        ) as BitmapDrawable,
//                        getDrawable(LocalContext.current, R.drawable.emoji_calm) as BitmapDrawable,
//                        getDrawable(LocalContext.current, R.drawable.emoji_happy) as BitmapDrawable
//                    ).toMutableList()
//                )
//            )
//        }

    // Design Pattern String Marker Gradient Line Chart using plot object
    CustomCard(title = "Gradient List using Plot Object") {

        LinearChart.GradientChart(
            linearData = LinearStringData(
                linearDataSets = linearDataSet4,
                xAxisLabels = xReadingMarker,
                yAxisLabels = Coordinate.coordinateSetBuilder(
                    "Very High",
                    "High",
                    "Moderate",
                    "Average",
                    "Bad"
                ).toMutableList()
            ),
            plot = LinearGradientPlot(
                colorList = listOf(
                    Color(0xFF4999DF).copy(alpha = .5f),
                    Color(0xFF4999DF).copy(alpha = .1f),
                )
            )
        )
    }

    // Design Pattern Custom Chart
//        CustomCard(
//            title = "Custom Chart"
//        ) {
//
//            LinearChart.CustomLinearChart(
//                linearData = LinearEmojiData(
//                    yAxisReadings = listOf(
//                        ChartPoint.pointDataBuilder(
//                            6f, 4f, 2f, 0f, 3f, 5f, 6f
//                        )
//                    ),
//                    xAxisReadings = ChartPoint.pointDataBuilder(
//                        "Jan", "Mar", "May", "Jul", "Sep", "Nov", "Dec"
//                    ),
//                    yMarkerList = ChartPoint.pointDataBuilder(
//                        getDrawable(
//                            LocalContext.current,
//                            R.drawable.emoji_furious
//                        ) as BitmapDrawable,
//                        getDrawable(LocalContext.current, R.drawable.emoji_angry) as BitmapDrawable,
//                        getDrawable(LocalContext.current, R.drawable.emoji_sad) as BitmapDrawable,
//                        getDrawable(
//                            LocalContext.current,
//                            R.drawable.emoji_depressed
//                        ) as BitmapDrawable,
//                        getDrawable(
//                            LocalContext.current,
//                            R.drawable.emoji_confused
//                        ) as BitmapDrawable,
//                        getDrawable(LocalContext.current, R.drawable.emoji_calm) as BitmapDrawable,
//                        getDrawable(LocalContext.current, R.drawable.emoji_happy) as BitmapDrawable
//                    ).toMutableList()
//                ),
//                plot = LinearGradientLinePlot(
//                    colorList = listOf(
//                        Color(0xFFE5E787).copy(alpha = .6f),
//                        Color(0xFF85DE50).copy(alpha = .6f),
//                        Color(0xFF57D6BF).copy(alpha = .6f),
//                        Color(0xFF43B4E4).copy(alpha = .6f),
//                        Color(0xFF3A60E6).copy(alpha = .6f),
//                        Color(0xFF57D6BF).copy(alpha = .6f),
//                        Color(0xFFD02596).copy(alpha = .6f)
//                    )
//                )
//            )
//        }

    // Design Pattern Bar Chart
    CustomCard(title = "Bar Chart") {

        LinearChart.BarChart(
            linearData = LinearStringData(
                linearDataSets = linearDataSet4,
                xAxisLabels = xReadingMarker
            )
        )
    }

    // Design Pattern Emoji Bar Chart
//        CustomCard(
//            title = "Emoji Bar Chart"
//        ) {
//
//            LinearChart.EmojiBarChart(
//                linearData = LinearEmojiData(
//                    yAxisReadings = dataSet1,
//                    xAxisReadings = xReadingMarker,
//                    yMarkerList = ChartPoint.pointDataBuilder(
//                        getDrawable(
//                            LocalContext.current,
//                            R.drawable.emoji_furious
//                        ) as BitmapDrawable,
//                        getDrawable(LocalContext.current, R.drawable.emoji_angry) as BitmapDrawable,
//                        getDrawable(LocalContext.current, R.drawable.emoji_sad) as BitmapDrawable,
//                        getDrawable(
//                            LocalContext.current,
//                            R.drawable.emoji_depressed
//                        ) as BitmapDrawable,
//                        getDrawable(
//                            LocalContext.current,
//                            R.drawable.emoji_confused
//                        ) as BitmapDrawable,
//                        getDrawable(LocalContext.current, R.drawable.emoji_calm) as BitmapDrawable,
//                        getDrawable(LocalContext.current, R.drawable.emoji_happy) as BitmapDrawable
//                    ).toMutableList()
//                )
//            )
//        }
}