package com.android.imageconverter.domain.model

import android.graphics.Bitmap

sealed class ImageStatus {
    data class Loading(val progress: Int) : ImageStatus()
    data class Successful(val image: Bitmap) : ImageStatus()
}