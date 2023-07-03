package com.example.madcamp_proj1

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Tab3Screen(modifier: Modifier = Modifier) {
    Text(
        text = "This is Tab3",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun Tab3ScreenPreview() {
    Tab3Screen()
}