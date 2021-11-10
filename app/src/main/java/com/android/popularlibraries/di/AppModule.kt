package com.android.popularlibraries.di

import com.android.popularlibraries.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }

}