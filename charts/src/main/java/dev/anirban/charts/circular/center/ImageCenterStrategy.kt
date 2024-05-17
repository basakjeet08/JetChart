package dev.anirban.charts.circular.center

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import dev.anirban.charts.circular.decoration.CircularDecoration
import dev.anirban.charts.circular.data.CircularDataStrategy


/**
 * This class is the implementation of [CircularCenterStrategy] which focuses on providing a
 * strategy for positioning an image in the center of a circular chart.
 *
 * This Class in particular is the implementation to draw an image
 *
 * For other implementations see [CustomCenterStrategy], [TextCenterStrategy], [NoCenterStrategy]
 *
 * @param modifier The modifier to be applied to the image.
 * @param image The image to be displayed in the center of the circular chart.
 * @param contentDescription The content description for the image.
 */
class ImageCenterStrategy(
    private val modifier: Modifier = Modifier,
    private val image: ImageVector = Icons.Default.Check,
    private val contentDescription: String? = null
) : CircularCenterStrategy {


    /**
     * This function draws composable function in the center of the chart
     *
     * @param circularData Data related to the circular chart.
     * @param decoration Decorations for the chart.
     */
    @Composable
    override fun DrawCenter(
        circularData: CircularDataStrategy,
        decoration: CircularDecoration
    ) {

        Icon(
            imageVector = image,
            contentDescription = contentDescription,
            modifier = modifier.size(24.dp)
        )
    }
}