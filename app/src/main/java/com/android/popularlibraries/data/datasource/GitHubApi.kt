package com.android.popularlibraries.data.datasource

import com.android.popularlibraries.data.model.GithubUser
import com.android.popularlibraries.data.model.UsersRepository
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface GitHubApi {

    @GET("/users")
    fun getGitHubUsers(): Single<List<GithubUser>>

    @GET("/users/{username}")
    fun getUserByLogin(
        @Path("username") login: String
    ): Single<GithubUser>

    @GET
    fun getUserRepos(@Url urlRepository: String): Single<List<UsersRepository>>
}