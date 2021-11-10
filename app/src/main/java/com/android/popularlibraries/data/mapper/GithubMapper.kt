package com.android.popularlibraries.data.mapper

import com.android.popularlibraries.data.model.GithubRepoNW
import com.android.popularlibraries.data.model.GithubUserNW
import com.android.popularlibraries.data.model.room.GithubRepoDB
import com.android.popularlibraries.data.model.room.GithubUserDB
import com.android.popularlibraries.domain.model.GithubRepo
import com.android.popularlibraries.domain.model.GithubUser

fun GithubUserNW.toDomain(): GithubUser =
    GithubUser(
        id = id,
        login = login,
        avatar = avatarUrl,
        reposUrl = reposUrl
    )

fun GithubRepoNW.toDomain(): GithubRepo =
    GithubRepo(
        id = id,
        userId = owner.id,
        name = name,
        private = private,
        forks = forks,
        description = description
    )

fun GithubUserNW.toDB(): GithubUserDB =
    GithubUserDB(
        id = id,
        login = login,
        avatar = avatarUrl,
        reposUrl = reposUrl
    )

fun GithubRepoNW.toDB(userId: Int): GithubRepoDB =
    GithubRepoDB(
        id = id,
        userId = userId,
        name = name,
        isPrivate = private,
        forks = forks,
        description = description ?: ""
    )

fun GithubRepoDB.toDomain(): GithubRepo =
    GithubRepo(id, userId, name, isPrivate, forks, description)

fun GithubUserDB.toDomain(): GithubUser =
    GithubUser(id, login, avatar, reposUrl)

fun GithubUser.toDB(): GithubUserDB =
    GithubUserDB(
        id = id,
        login = login,
        avatar = avatar ?: "",
        reposUrl = reposUrl
    )

fun GithubRepo.toDB(): GithubRepoDB =
    GithubRepoDB(
        id = id,
        userId = userId,
        name = name,
        isPrivate = private,
        forks = forks,
        description = description ?: ""
    )