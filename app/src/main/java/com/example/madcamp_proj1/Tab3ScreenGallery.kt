package com.example.madcamp_proj1

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.madcamp_proj1.data.Datasource

@Composable
fun Tab3ScreenGallery(modifier: Modifier = Modifier) {
    GalleryImageGrid(
        galleryImageList = Datasource().loadGalleryImages(),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun Tab3ScreenGalleryPreview() {
    Tab3ScreenGallery()
}