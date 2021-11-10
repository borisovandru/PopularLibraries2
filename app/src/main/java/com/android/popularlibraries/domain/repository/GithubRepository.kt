package com.android.popularlibraries.domain.repository

import com.android.popularlibraries.domain.model.GithubRepo
import com.android.popularlibraries.domain.model.GithubUser
import io.reactivex.Single

interface GithubRepository {
    fun getUsers(): Single<List<GithubUser>>
    fun getUserRepos(user: GithubUser): Single<List<GithubRepo>>
}