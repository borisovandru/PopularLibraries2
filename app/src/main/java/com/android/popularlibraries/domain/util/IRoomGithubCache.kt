package com.android.popularlibraries.domain.util

import com.android.popularlibraries.domain.model.GithubRepo
import com.android.popularlibraries.domain.model.GithubUser
import io.reactivex.Single

interface IRoomGithubCache {
    fun cacheUsers(users: List<GithubUser>)
    fun cacheRepos(repos: List<GithubRepo>)
    fun getCachedUsers(): Single<List<GithubUser>>
    fun getCachedRepos(userId: Int): Single<List<GithubRepo>>
}