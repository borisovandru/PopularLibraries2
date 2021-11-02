package com.android.popularlibraries.data.repository

import com.android.popularlibraries.data.GithubUser
import com.android.popularlibraries.data.UsersRepository
import io.reactivex.rxjava3.core.Single

interface GithubUsersRepo {

    fun githubUsers(): Single<List<GithubUser>>
    fun userRepos(repoUrl: String): Single<List<UsersRepository>>
    fun githubUser(login: String): Single<GithubUser>

}