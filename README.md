
# JetChart

JetChart is a light chart library for Android, which is currently under development. It’s only compatible with Jetpack Compose. It is made primarily with Kotlin, Kotlin DrawScope, and Canvas Functions. The library contains some basic charts and graphs like ring, donut, arc, line, bar, gradient, etc.


![Kotlin Version](https://img.shields.io/badge/Kotlin-1.8.0-8A2BE2)

[![](https://jitpack.io/v/basakjeet08/JetChart.svg)](https://jitpack.io/#basakjeet08/JetChart)

[![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-yellow.svg)](https://opensource.org/licenses/)
## Features

- Light/dark mode Support
- Easy Integration
- Fully Supports Jetpack Compose


## Screenshots

![Light Theme](https://github.com/basakjeet08/JetChart/assets/24616267/390b1c29-b800-477c-a5af-2f4fcce1f9b0)

![Dark Theme](https://github.com/basakjeet08/JetChart/assets/24616267/9904f61b-941a-4eb2-96a0-b38a3eab9bd6)

## Installation

To Add the dependency into your project first add this to your root build.gradle file

```
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
  }
}
```

After that add the Dependency 

```
implementation 'com.github.basakjeet08:JetChart:1.1.1'
```
## Usage/Examples

Defining Variables for the Charts.

```Kotlin
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

```Kotlin
  LinearChart.LineChart(
    linearData = LinearStringData(
      yAxisReadings = dataSet1,
        xAxisReadings = xReadingMarker
    )
  )
```

```Kotlin
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

