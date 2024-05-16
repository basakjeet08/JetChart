package dev.anirban.jetchart.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


/**
 * This is the primary button composable which create s button.
 *
 * @param modifier Default modifier from the parent function.
 * @param text Text to be shown in the Button
 * @param onClick Function to be invoked when the button is clicked
 */
@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {

    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 4.dp),
            text = text
        )
    }
}