package com.android.popularlibraries.data.di

import android.content.Context
import androidx.room.Room
import com.android.popularlibraries.data.domain.EventBus
import com.android.popularlibraries.data.room.GithubDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

const val DB_NAME = "githubDB"

@Module
class AppModule(val context: Context) {

    @Singleton
    @Provides
    fun provideContext(): Context {
        return context
    }

    @Singleton
    @Provides
    fun provideCreateDB(context: Context): GithubDatabase {
        return Room.databaseBuilder(context, GithubDatabase::class.java, DB_NAME).build()
    }

    @Singleton
    @Provides
    fun provideEventBus(): EventBus {
        return EventBus
    }

}