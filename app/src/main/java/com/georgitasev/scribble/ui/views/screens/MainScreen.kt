package com.georgitasev.scribble.ui.views.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.georgitasev.scribble.ui.views.CoreScreen
import com.georgitasev.scribble.ui.views.composables.ListTileItem

@Composable
fun MainScreen() {
    CoreScreen(
        hasFAB = true,
        body = {
                innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(
                        horizontal = 12.dp,
                        vertical = 10.dp
                    )
            ) {
                items(count = 1) {
                    ListTileItem(title = "Hello World")
                }

                items(count = 2) {
                    ListTileItem(title = "Hello World", description = "dhakjshdkjahskjd")
                }

                items(count = 1) {
                    ListTileItem(title = "Hello World")
                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    CoreScreen(
        hasFAB = true,
        body = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(
                        horizontal = 12.dp,
                        vertical = 10.dp
                    )
            ) {
                items(count = 1) {
                    ListTileItem(title = "Hello World")
                }

                items(count = 2) {
                    ListTileItem(title = "Hello World", description = "dhakjshdkjahskjd")
                }

                items(count = 1) {
                    ListTileItem(title = "Hello World")
                }
            }
        }
    )
}