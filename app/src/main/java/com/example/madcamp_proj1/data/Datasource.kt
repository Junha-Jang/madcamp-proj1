package com.example.madcamp_proj1.data

import com.example.madcamp_proj1.R
import com.example.madcamp_proj1.model.GalleryImage

class Datasource() {
    fun loadGalleryImages(): List<GalleryImage> {
        return listOf<GalleryImage>(
            GalleryImage(R.drawable.image1),
            GalleryImage(R.drawable.image2),
            GalleryImage(R.drawable.image3),
            GalleryImage(R.drawable.image4),
            GalleryImage(R.drawable.image5),
            GalleryImage(R.drawable.image6),
            GalleryImage(R.drawable.image7),
            GalleryImage(R.drawable.image8),
            GalleryImage(R.drawable.image9),
            GalleryImage(R.drawable.image10)
        )
    }
}
