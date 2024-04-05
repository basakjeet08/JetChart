package dev.anirban.jetchart.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.anirban.charts.circular.CircularChart
import dev.anirban.charts.circular.center.CircularImageCenter
import dev.anirban.charts.circular.center.CircularRingTextCenter
import dev.anirban.charts.circular.center.CircularTargetTextCenter
import dev.anirban.charts.circular.charts.CircularDonutChartColumn
import dev.anirban.charts.circular.charts.CircularDonutChartRow
import dev.anirban.charts.circular.charts.CircularRingChart
import dev.anirban.charts.circular.data.CircularDonutListData
import dev.anirban.charts.circular.data.CircularTargetDataBuilder
import dev.anirban.charts.circular.foreground.CircularDonutForeground
import dev.anirban.charts.circular.foreground.CircularDonutTargetForeground
import dev.anirban.charts.linear.LinearChart
import dev.anirban.charts.linear.data.LinearStringData
import dev.anirban.charts.linear.plots.LinearGradientLinePlot
import dev.anirban.charts.util.ChartPoint
import dev.anirban.jetchart.ui.theme.JetChartTheme


// Preview Composable Function
@Preview(
    "Light",
    heightDp = 2000,
    showBackground = true
)
@Preview(
    name = "Dark",
    heightDp = 2000,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun DefaultPreview() {
    JetChartTheme {
        LibraryUIExample()
    }
}

@Composable
fun LibraryUIExample() {


    // Example Data Sets
    val xReadingMarker by remember {
        mutableStateOf(ChartPoint.pointDataBuilder("A", "B", "C", "D", "E", "F", "G"))
    }
    var dataSet1 by remember {
        mutableStateOf(listOf(ChartPoint.pointDataBuilder(6f, 5f, 4f, 6f, 7.5f, 7f, 6f)))
    }
    var dataSet2 by remember {
        mutableStateOf(
            listOf(
                ChartPoint.pointDataBuilder(6f, 5f, 4f, 6f, 7.5f, 7f, 6f),
                ChartPoint.pointDataBuilder(3f, 6f, 8f, 2f, 3.5f, 3f, 4f)
            )
        )
    }
    var dataSet3 by remember {
        mutableStateOf(
            listOf(
                ChartPoint.pointDataBuilder(6f, 5f, 4f, 6f, 7.5f, 7f, 6f),
                ChartPoint.pointDataBuilder(3f, 6f, 8f, 2f, 3.5f, 3f, 4f),
                ChartPoint.pointDataBuilder(1f, 8f, 4f, 3f, 5.9f, 2.9f, 4.7f)
            )
        )
    }
    var dataSet4 by remember {
        mutableStateOf(listOf(ChartPoint.pointDataBuilder(4f, 0f, 1.7f, 1.9f, 2f, 4f)))
    }

    // This variable helps to change data Set when the button is clicked.
    var onButtonPress by remember { mutableStateOf(false) }

    // Changing the Data Set here.
    LaunchedEffect(onButtonPress) {
        val mutableList1: MutableList<Float> = mutableListOf()
        val mutableList2: MutableList<Float> = mutableListOf()
        val mutableList3: MutableList<Float> = mutableListOf()
        val mutableList4: MutableList<Float> = mutableListOf()

        for (i in 0..6) {
            mutableList1.add((1..10).random().toFloat())
            mutableList2.add((1..10).random().toFloat())
            mutableList3.add((1..10).random().toFloat())
            mutableList4.add((0..4).random().toFloat())
        }

        dataSet1 = listOf(ChartPoint.pointDataBuilder(mutableList1))
        dataSet2 = listOf(
            ChartPoint.pointDataBuilder(mutableList1),
            ChartPoint.pointDataBuilder(mutableList2)
        )
        dataSet3 = listOf(
            ChartPoint.pointDataBuilder(mutableList1),
            ChartPoint.pointDataBuilder(mutableList2),
            ChartPoint.pointDataBuilder(mutableList3)
        )
        dataSet4 = listOf(ChartPoint.pointDataBuilder(mutableList4))
        onButtonPress = false
    }


    // Main UI
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = { onButtonPress = true }
        ) {
            Text(text = "Reload DataSet")
        }

        // Design Pattern Single Line Chart
        CustomCard(title = "Single Line Chart") {

            LinearChart.LineChart(
                linearData = LinearStringData(
                    yAxisReadings = dataSet1,
                    xAxisReadings = xReadingMarker
                )
            )
        }

        // Design Pattern Double Line Chart
        CustomCard(title = "Double Line Chart") {

            LinearChart.LineChart(
                linearData = LinearStringData(
                    yAxisReadings = dataSet2,
                    xAxisReadings = xReadingMarker
                )
            )
        }

        // Design Pattern Triple Line Chart
        CustomCard(title = "Multiple Line Chart") {

            LinearChart.LineChart(
                linearData = LinearStringData(
                    yAxisReadings = dataSet3,
                    xAxisReadings = xReadingMarker
                )
            )
        }

        // Design Pattern String Marker Line Chart
        CustomCard(title = "String Marker Chart") {
            LinearChart.LineChart(
                linearData = LinearStringData(
                    yAxisReadings = dataSet4,
                    xAxisReadings = xReadingMarker,
                    yMarkerList = ChartPoint.pointDataBuilder(
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
                    yAxisReadings = dataSet4,
                    xAxisReadings = xReadingMarker,
                    yMarkerList = ChartPoint.pointDataBuilder(
                        "Very High",
                        "High",
                        "Moderate",
                        "Average",
                        "Bad"
                    ).toMutableList()
                ),
                plot = LinearGradientLinePlot(
                    colorList = listOf(
                        Color(0xFF85DE50).copy(alpha = .5f),
                        Color(0xFF85DE50).copy(alpha = .1f),
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
                    yAxisReadings = dataSet1,
                    xAxisReadings = xReadingMarker
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

        // Design Pattern Same row Donut Chart
        CustomCard(title = " Row Donut Chart") {

            CircularDonutChartRow.DonutChartRow(
                circularData = CircularDonutListData(
                    itemsList = listOf(
                        Pair("Fruit", 1500.0f),
                        Pair("Junk Food", 300.0f),
                        Pair("Protein", 500.0f)
                    ),
                    siUnit = "Kcal",
                    cgsUnit = "cal",
                    conversionRate = { it / 1000f }
                ),
                circularForeground = CircularDonutForeground(
                    radiusMultiplier = 1.7f,
                    strokeWidth = 15f,
                    startAngle = 30f
                )
            )
        }

        // Design Pattern Different row Donut Chart
        CustomCard(title = "Column Donut Chart") {

            CircularDonutChartColumn.DonutChartColumn(
                circularData = CircularDonutListData(
                    itemsList = listOf(
                        Pair("Study", 450f),
                        Pair("Sport", 180f),
                        Pair("Social", 30f),
                        Pair("Others", 60f)
                    ),
                    siUnit = "Hrs",
                    cgsUnit = "Min",
                    conversionRate = { it / 60f }
                )
            )
        }

        // Design Pattern Target Achieved Donut Chart
        CustomCard(title = "Target Donut Chart") {

            CircularDonutChartRow.TargetDonutChart(
                circularData = CircularTargetDataBuilder(
                    target = 4340f,
                    achieved = 2823f,
                    siUnit = "Km",
                    cgsUnit = "m",
                    conversionRate = { it / 1000f }
                ),
                circularCenter = CircularTargetTextCenter(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700
                )
            )
        }

        // Design Pattern Single Ring Chart
        CustomCard(title = "Single Ring Chart") {
            CircularRingChart.SingleRingChart(
                circularData = CircularTargetDataBuilder(
                    target = 500f,
                    achieved = 489f,
                    siUnit = "",
                    cgsUnit = "",
                    conversionRate = { it }
                ),
                circularCenter = CircularRingTextCenter(
                    title = "Title 1",
                    centerValue = "value",
                    status = "status Value"
                )
            )
        }


        // Design Pattern Double Ring Chart
        CustomCard(title = "Double Ring Chart") {
            CircularRingChart.MultipleRingChart(
                circularData = listOf(
                    CircularTargetDataBuilder(
                        target = 100f,
                        achieved = 81f,
                        siUnit = "bpm",
                        cgsUnit = "bpm",
                        conversionRate = { it }
                    ),
                    CircularTargetDataBuilder(
                        target = 160f,
                        achieved = 112f,
                        siUnit = "mm",
                        cgsUnit = "mm",
                        conversionRate = { it }
                    )
                ),
                circularCenter = listOf(
                    CircularRingTextCenter(
                        title = "Title 1",
                        centerValue = "center value",
                        status = "Not Good"
                    ),
                    CircularRingTextCenter(
                        title = "Title 2",
                        centerValue = "center value",
                        status = "Great"
                    )
                )
            )
        }

        CustomCard(title = "Weekly Progress") {
            Row {

                listOf("M", "T", "W", "T", "F", "S", "S").forEach {

                    Column(
                        modifier = Modifier
                            .weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        CircularChart.DonutChartImage(
                            modifier = Modifier
                                .size(55.dp),
                            circularData = CircularTargetDataBuilder(
                                target = 100f,
                                achieved = 81f,
                                siUnit = "",
                                cgsUnit = "",
                                conversionRate = { it }
                            ),
                            circularCenter = CircularImageCenter(
                                image = Icons.Default.Check,
                                contentDescription = "Achieved"
                            ),
                            circularForeground = CircularDonutTargetForeground(strokeWidth = 10f)
                        )

                        Text(
                            text = it,

                            // Text Features
                            textAlign = TextAlign.Start,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W700,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}