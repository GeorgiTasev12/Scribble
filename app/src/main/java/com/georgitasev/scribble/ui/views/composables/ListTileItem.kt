package com.georgitasev.scribble.ui.views.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListTileItem(
    title: String,
    description: String = ""
) {
    Surface(
        shape = AbsoluteRoundedCornerShape(percent = 14),
        color = MaterialTheme.colorScheme.surfaceContainer,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    title,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                description.takeIf { it.isNotBlank() }?.let { text ->
                    Text(
                        text,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Monospace,
                        maxLines = 2,
                        softWrap = true,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
            ComboBoxMenu()
        }
    }
}
