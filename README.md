
# JetChart

JetChart is a light chart library for Android, which is currently under development. It’s compatible with only Jetpack Compose. It is made primarily with Kotlin and Kotlin DrawScope and Canvas Functions.This library contains some basic charts like ring , donut , arc , line , bar , gradient etc.


![Kotlin Version](https://img.shields.io/badge/Kotlin-1.8.0-8A2BE2)

![Current Version](https://img.shields.io/badge/Version-v1.0.1-8A2BE2)

[![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-yellow.svg)](https://opensource.org/licenses/)
## Features

- Light/dark mode Support
- Easy Integration
- Fully Supports Jetpack Compose


## Screenshots

![IMG-20240405-WA0019](https://github.com/basakjeet08/JetChart/assets/24616267/eda6e515-04a7-484c-b6bf-ffe626e11610)

![IMG-20240405-WA0018](https://github.com/basakjeet08/JetChart/assets/24616267/dffcb148-1c8c-4132-913a-a045acc62b36)

![IMG-20240405-WA0017](https://github.com/basakjeet08/JetChart/assets/24616267/43885ed4-00ad-43cc-a8d3-d1cc47266310)

![IMG-20240405-WA0015](https://github.com/basakjeet08/JetChart/assets/24616267/5df20b0d-ef0b-4155-837a-f327766b610c)
## Usage/Examples

Defining Variables for the Charts.

```kotlin
  val xReadingMarker by remember {
    mutableStateOf(ChartPoint.pointDataBuilder("A", "B", "C", "D", "E", "F", "G"))
  }

  val dataSet1 by remember {
    mutableStateOf(listOf(ChartPoint.pointDataBuilder(6f, 5f, 4f, 6f, 7.5f, 7f, 6f)))
  }

  val dataSet2 by remember {
    mutableStateOf(
      listOf(
        ChartPoint.pointDataBuilder(6f, 5f, 4f, 6f, 7.5f, 7f, 6f),
        ChartPoint.pointDataBuilder(3f, 6f, 8f, 2f, 3.5f, 3f, 4f)
      )
    )
  }
```

Making a Simple Line Chart.

```kotlin
  LinearChart.LineChart(
    linearData = LinearStringData(
      yAxisReadings = dataSet1,
        xAxisReadings = xReadingMarker
    )
  )
```

```kotlin
  LinearChart.LineChart(
    linearData = LinearStringData(
      yAxisReadings = dataSet2,
      xAxisReadings = xReadingMarker
    )
  )
```
## Tech Stack

**Language:** Kotlin.


## Authors

- [@basakjeet08](https://www.github.com/basakjeet08)


## License

JetChart is [GPL licensed](./LICENSE)
## Support

For support, email jeetbasak2002@gmail.com.

