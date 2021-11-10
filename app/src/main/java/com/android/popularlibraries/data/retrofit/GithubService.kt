package com.android.popularlibraries.data.retrofit

import com.android.popularlibraries.data.model.GithubRepoNW
import com.android.popularlibraries.data.model.GithubUserNW
import com.android.popularlibraries.domain.model.GithubUser
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface GithubService {
    @GET("/users")
    fun getUsers(): Single<List<GithubUserNW>>

    @GET("/users/{username}")
    fun getUserByLogin(
        @Path("username") login: String
    ): Single<GithubUser>

    @GET
    fun getUserRepos(
        @Url url: String
    ): Single<List<GithubRepoNW>>
}