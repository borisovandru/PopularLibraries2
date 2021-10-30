package com.android.popularlibraries.data.repository

import com.android.popularlibraries.data.model.GithubUser

class GithubUsersRepository {

    private val repository = (0..100).map { GithubUser("login $it") }

    fun getUsers() = repository
}