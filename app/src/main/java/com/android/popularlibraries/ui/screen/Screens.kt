package com.android.popularlibraries.ui.screen

import com.android.popularlibraries.domain.model.GithubRepo
import com.android.popularlibraries.domain.model.GithubUser
import com.android.popularlibraries.ui.fragment.RepoDetailFragment
import com.android.popularlibraries.ui.fragment.UserDetailFragment
import com.android.popularlibraries.ui.fragment.UserReposFragment
import com.android.popularlibraries.ui.fragment.UsersFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class Screens : IScreens {
    override fun users(): Screen =
        FragmentScreen { UsersFragment.newInstance() }

    override fun userDetail(user: GithubUser): Screen =
        FragmentScreen { UserDetailFragment.newInstance(user) }

    override fun userRepos(user: GithubUser): Screen =
        FragmentScreen { UserReposFragment.newInstance(user) }

    override fun repoDetails(repo: GithubRepo): Screen =
        FragmentScreen { RepoDetailFragment.newInstance(repo) }
}