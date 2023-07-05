package com.example.madcamp_proj1.model

import androidx.annotation.DrawableRes

data class GalleryImage(
    @DrawableRes val imageResourceId: Int?,
    val nameList: List<String> = listOf(),
    val time: String = "2023/07/05"
)