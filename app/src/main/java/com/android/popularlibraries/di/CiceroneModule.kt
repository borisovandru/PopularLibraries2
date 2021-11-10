package com.android.popularlibraries.di

import com.android.popularlibraries.ui.screen.IScreens
import com.android.popularlibraries.ui.screen.Screens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CiceroneModule {

    val cicerone = Cicerone.create()

    @Provides
    fun provideCicerone(): Cicerone<Router> =
        cicerone

    @Singleton
    @Provides
    fun provideNavigatorHolder(): NavigatorHolder =
        cicerone.getNavigatorHolder()

    @Singleton
    @Provides
    fun provideRouter(): Router =
        cicerone.router

    @Singleton
    @Provides
    fun provideScreens(): IScreens =
        Screens()
}