package com.android.popularlibraries.data.di

import android.content.Context
import com.android.popularlibraries.rx.SchedulerProvider
import com.android.popularlibraries.data.datasource.GitHubApi
import com.android.popularlibraries.data.domain.NetworkStatusImpl
import com.android.popularlibraries.data.repository.GithubUserRepoCombinedImpl
import com.android.popularlibraries.data.repository.GithubUsersLocalRepoImpl
import com.android.popularlibraries.data.repository.GithubUsersRepo
import com.android.popularlibraries.data.repository.GithubUsersWebRepoImpl
import com.android.popularlibraries.data.room.GithubDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun provideRepo(db: GithubDatabase, githubApi: GitHubApi, context: Context): GithubUsersRepo {
        return GithubUserRepoCombinedImpl(
            GithubUsersLocalRepoImpl(db),
            GithubUsersWebRepoImpl(githubApi),
            NetworkStatusImpl(context),
            SchedulerProvider()
        )
    }

}