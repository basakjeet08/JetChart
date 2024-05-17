package dev.anirban.jetchart.view.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.anirban.jetchart.view.components.CustomButton
import dev.anirban.jetchart.view.navigation.CIRCULAR_CHART_UI_ROUTE
import dev.anirban.jetchart.view.navigation.LINEAR_CHART_UI_ROUTE
import dev.anirban.jetchart.view.theme.JetChartTheme


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
        HomeScreen {}
    }
}

@Composable
fun HomeScreen(navigateTo: (String) -> Unit) {

    // Main UI
    Column(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Button to navigate to Linear Chart UI
        CustomButton(
            text = "Linear Chart Examples",
            onClick = { navigateTo(LINEAR_CHART_UI_ROUTE) }
        )

        Spacer(Modifier.height(16.dp))

        // Button to navigate to Circular Chart UI
        CustomButton(
            text = "Circular Chart Examples",
            onClick = { navigateTo(CIRCULAR_CHART_UI_ROUTE) }
        )
    }
}