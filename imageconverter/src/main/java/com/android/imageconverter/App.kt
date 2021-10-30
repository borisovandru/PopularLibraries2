package com.android.imageconverter

import android.app.Application
import io.reactivex.plugins.RxJavaPlugins
import com.android.imageconverter.data.repository.MainRepositoryImpl
import com.android.imageconverter.domain.repository.MainRepository

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    val mainRepository: MainRepository = MainRepositoryImpl()

    override fun onCreate() {
        super.onCreate()
        instance = this
        RxJavaPlugins.setErrorHandler { }
    }
}