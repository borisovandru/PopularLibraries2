package com.android.imageconverter.data.repository

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import com.android.imageconverter.domain.model.ImageStatus
import com.android.imageconverter.domain.model.ImageType
import com.android.imageconverter.domain.repository.MainRepository
import java.io.FileOutputStream

private const val LOAD_DELAY = 40L
private const val DELAY_DIVIDER = 10
private const val MAX_PERCENT = 100
private const val CONVERT_ERROR = "Ошибка преобразование в "
private const val PNG_QUANTITY = 75

class MainRepositoryImpl : MainRepository {
    @SuppressLint("NewApi")
    override fun loadImage(image: String, type: ImageType): Observable<ImageStatus> =
        Observable
            .create<ImageStatus> { emitter ->
                (0..MAX_PERCENT).map {
                    emitter.onNext(ImageStatus.Loading(it))
                    Thread.sleep(LOAD_DELAY)
                }
                emitter.onNext(
                    ImageStatus.Successful(
                        BitmapFactory.decodeFile(image + type.ext)
                    )
                )
                emitter.onComplete()
            }
            .subscribeOn(Schedulers.io())

    override fun convertToPng(
        image: String,
        imageType: ImageType,
        bitmapSource: Bitmap?
    ): Observable<ImageStatus> =
        Observable
            .create<ImageStatus> { emitter ->
                (0..MAX_PERCENT).map {
                    emitter.onNext(ImageStatus.Loading(it))
                    Thread.sleep(LOAD_DELAY / DELAY_DIVIDER)
                }
                val convertResult = bitmapSource?.compress(
                    Bitmap.CompressFormat.PNG,
                    PNG_QUANTITY,
                    FileOutputStream(image + imageType.ext)
                )
                if (convertResult == null || !convertResult) {
                    emitter.onError(Throwable(CONVERT_ERROR + imageType.ext))
                } else {
                    emitter.onComplete()
                }
            }
            .subscribeOn(Schedulers.io())
}