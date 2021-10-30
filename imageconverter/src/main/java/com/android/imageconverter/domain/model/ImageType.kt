package com.android.imageconverter.domain.model

sealed class ImageType(val ext: String) {
    object Jpg : ImageType(".jpg")
    object Png : ImageType(".png")
}