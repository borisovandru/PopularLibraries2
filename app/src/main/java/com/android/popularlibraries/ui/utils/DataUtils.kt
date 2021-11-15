package com.android.popularlibraries.ui.utils

import android.content.Context
import android.widget.Toast
import com.android.popularlibraries.App
import com.android.popularlibraries.data.model.GithubUser
import com.android.popularlibraries.data.model.UsersRepository
import com.android.popularlibraries.data.room.GithubRepositoryEntity
import com.android.popularlibraries.data.room.GithubUserEntity

fun errorMessage(context: Context?, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

val Context.app: App
    get() {
        return applicationContext as App
    }

fun githubUserListMap(users: List<GithubUserEntity>) =
    users.map {
        githubUserMap(it)
    }

fun githubUserMap(githubUser: GithubUserEntity) = GithubUser(
    githubUser.avatarUrl,
    githubUser.id,
    githubUser.login,
    githubUser.organizationsUrl,
    githubUser.reposUrl,
    githubUser.like
)

fun usersReposMap(repos: List<GithubRepositoryEntity>) =
    repos.map {
        UsersRepository(
            it.htmlUrl,
            it.id,
            it.userId,
            it.name,
            it.description,
            it.likeCounter,
        )
    }

fun githubUserEntityMap(githubUser: GithubUser) = GithubUserEntity(
    githubUser.avatarUrl,
    githubUser.id,
    githubUser.login,
    githubUser.organizationsUrl,
    githubUser.reposUrl,
    githubUser.like
)

fun githubUserEntityListMap(users: List<GithubUser>) =
    users.map {
        githubUserEntityMap(it)
    }

fun usersReposEntityMap(repos: List<UsersRepository>, userId: Int?) =
    repos.map {
        GithubRepositoryEntity(
            it.htmlUrl,
            it.id,
            userId,
            it.name,
            it.description,
            it.likeCounter,
        )
    }
