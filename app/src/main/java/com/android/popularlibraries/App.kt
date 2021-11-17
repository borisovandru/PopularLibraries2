package com.android.popularlibraries

import android.app.Application
import com.android.popularlibraries.data.di.AppComponent
import com.android.popularlibraries.data.di.AppModule
import com.android.popularlibraries.data.di.DaggerAppComponent
import com.facebook.stetho.Stetho

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}

