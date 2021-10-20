package com.android.popularlibraries.presentation.utils.screens

import com.android.popularlibraries.data.model.GithubUser
import com.android.popularlibraries.presentation.view.fragments.user_details.UserDetailsFragment
import com.android.popularlibraries.presentation.view.fragments.user_list.fragment.UserListFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class Screens : IScreens {

    override fun openUserListFragment() =
        FragmentScreen { UserListFragment.newInstance() }

    override fun openUserDetailsFragment(user: GithubUser) =
        FragmentScreen { UserDetailsFragment.newInstance(user) }
}