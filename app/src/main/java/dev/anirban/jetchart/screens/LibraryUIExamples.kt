package dev.anirban.jetchart.screens

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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

}