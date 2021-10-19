package com.android.popularlibraries.domain.repository

import com.android.popularlibraries.domain.model.GithubUser

class GithubUsersRepo {
    private val repositories =
        (0..100).map { GithubUser("user$it") }

    fun getUsers(): List<GithubUser> {
        return repositories
    }
}