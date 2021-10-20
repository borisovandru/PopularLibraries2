package com.android.popularlibraries.presentation.presenter.user_details

import com.android.popularlibraries.data.model.GithubUser
import com.android.popularlibraries.presentation.view.fragments.user_details.IUserDetailsView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserDetailsPresenter(
    private val router: Router
) : MvpPresenter<IUserDetailsView>() {

    var user: GithubUser? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}