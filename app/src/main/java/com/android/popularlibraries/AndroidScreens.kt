package com.android.popularlibraries

import com.android.popularlibraries.data.GithubUser
import com.android.popularlibraries.ui.profile.ProfileFragment
import com.android.popularlibraries.ui.users.UsersFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun profile(githubUser: GithubUser) =
        FragmentScreen { ProfileFragment.newInstance(githubUser) }
}

interface IScreens {
    fun users(): Screen
    fun profile(githubUser: GithubUser): Screen
}