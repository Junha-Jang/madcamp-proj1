package com.example.madcamp_proj1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Tab3Screen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Tab3ScreenContact()
        Tab3ScreenGallery()
    }
}

@Preview(showBackground = true)
@Composable
fun Tab3ScreenPreview() {
    Tab3Screen()
}