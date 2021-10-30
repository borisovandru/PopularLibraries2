package com.android.popularlibraries.presentation.utils.screens

import com.android.popularlibraries.data.model.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun openUserListFragment(): Screen
    fun openUserDetailsFragment(user: GithubUser): Screen
}