package com.android.popularlibraries.data.repository

import com.android.popularlibraries.data.model.GithubUser
import com.android.popularlibraries.data.model.UsersRepository
import com.android.popularlibraries.data.room.GithubRepositoryEntity
import com.android.popularlibraries.data.room.GithubUserEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface GithubLocalRepo {

    fun githubUsers(): Single<List<GithubUser>>
    fun userRepos(repoUrl: String): Single<List<UsersRepository>>
    fun githubUser(login: String): Single<GithubUser>
    fun putGithubUser(users: List<GithubUserEntity>): Completable
    fun putGithubUser(user: GithubUserEntity): Completable
    fun putGithubRepos(repos: List<GithubRepositoryEntity>): Completable

}