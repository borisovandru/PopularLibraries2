package com.android.imageconverter.domain.repository

import android.graphics.Bitmap
import io.reactivex.Observable
import com.android.imageconverter.domain.model.ImageStatus
import com.android.imageconverter.domain.model.ImageType

interface MainRepository {
    fun loadImage(image: String, type: ImageType): Observable<ImageStatus>
    fun convertToPng(
        image: String,
        imageType: ImageType,
        bitmapSource: Bitmap?
    ): Observable<ImageStatus>
}