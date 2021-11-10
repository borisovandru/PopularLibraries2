package com.android.popularlibraries

import android.app.Application
import com.android.popularlibraries.di.AppComponent
import com.android.popularlibraries.di.AppModule
import com.android.popularlibraries.di.DaggerAppComponent

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}