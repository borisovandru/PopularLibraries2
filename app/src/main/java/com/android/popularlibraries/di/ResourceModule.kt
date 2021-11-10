package com.android.popularlibraries.di

import android.content.Context
import com.android.popularlibraries.App
import dagger.Module
import dagger.Provides

@Module
class ResourceModule {

    @Provides
    fun provideContext(app: App): Context =
        app.applicationContext
}