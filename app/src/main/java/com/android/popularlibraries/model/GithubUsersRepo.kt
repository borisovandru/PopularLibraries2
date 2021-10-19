package com.android.popularlibraries.model

class GithubUsersRepo {
    private val repositories =
        (0..100).map { GithubUser("user$it", "pass$it") }

    fun getUsers(): List<GithubUser> {
        return repositories
    }
}