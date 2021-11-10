package com.android.popularlibraries.di

import android.content.Context
import androidx.room.Room
import com.android.popularlibraries.data.repository.GithubRepositoryWithCacheImpl
import com.android.popularlibraries.data.repository.RoomGithubCacheImpl
import com.android.popularlibraries.data.retrofit.GithubService
import com.android.popularlibraries.data.room.GithubDatabase
import com.android.popularlibraries.domain.repository.GithubRepository
import com.android.popularlibraries.domain.util.INetworkStatus
import com.android.popularlibraries.domain.util.IRoomGithubCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideGithubRepository(
        githubService: GithubService,
        networkStatus: INetworkStatus,
        roomCache: IRoomGithubCache
    ): GithubRepository =
        GithubRepositoryWithCacheImpl(githubService, networkStatus, roomCache)

    @Singleton
    @Provides
    fun provideRoomGithubCache(database: GithubDatabase): IRoomGithubCache =
        RoomGithubCacheImpl(database)

    @Singleton
    @Provides
    fun provideGithubDatabase(context: Context): GithubDatabase =
        Room.databaseBuilder(
            context, GithubDatabase::class.java,
            "database.db"
        )
            .build()
}