package com.android.popularlibraries.data.repository

import com.android.popularlibraries.data.mapper.toDomain
import com.android.popularlibraries.data.retrofit.GithubService
import com.android.popularlibraries.domain.model.GithubRepo
import com.android.popularlibraries.domain.model.GithubUser
import com.android.popularlibraries.domain.repository.GithubRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

private const val HTTP_LOG_TAG = "HTTP_LOG"

class GithubRepositoryImpl(
    private val githubService: GithubService
) : GithubRepository {

    override fun getUsers(): Single<List<GithubUser>> =
        githubService.getUsers()
            .map { resultList ->
                resultList.map { it.toDomain() }
            }
            .subscribeOn(Schedulers.io())

    override fun getUserRepos(user: GithubUser): Single<List<GithubRepo>> =
        githubService.getUserRepos(user.reposUrl)
            .map { list ->
                list.map { it.toDomain() }
            }
            .subscribeOn(Schedulers.io())
}