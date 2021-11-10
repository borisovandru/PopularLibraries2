package com.android.popularlibraries.ui.screen

import com.android.popularlibraries.domain.model.GithubRepo
import com.android.popularlibraries.domain.model.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun userDetail(user: GithubUser): Screen
    fun userRepos(user: GithubUser): Screen
    fun repoDetails(repo: GithubRepo): Screen
}