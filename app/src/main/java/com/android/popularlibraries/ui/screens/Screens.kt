package com.android.popularlibraries.ui.screens

import com.android.popularlibraries.domain.model.GithubUser
import com.android.popularlibraries.ui.fragment.UserDetailsFragment
import com.android.popularlibraries.ui.fragment.UserListFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class Screens : IScreens {
    override fun userList(): Screen {
        return FragmentScreen { UserListFragment.newInstance() }
    }

    override fun userDetail(user: GithubUser): Screen {
        return FragmentScreen { UserDetailsFragment.newInstance(user) }
    }
}