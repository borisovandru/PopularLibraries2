package com.android.popularlibraries.ui.screens

import com.android.popularlibraries.domain.model.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun userList(): Screen
    fun userDetail(user: GithubUser): Screen
}
