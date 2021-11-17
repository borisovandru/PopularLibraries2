package com.android.popularlibraries.data.repository

import com.android.popularlibraries.data.model.GithubUser
import com.android.popularlibraries.data.model.UsersRepository
import io.reactivex.rxjava3.core.Single

interface GithubUsersRepo {

    fun githubUsers(): Single<List<GithubUser>>
    fun userRepos(repoUrl: String, userId: Int?): Single<List<UsersRepository>>
    fun githubUser(login: String): Single<GithubUser>

}