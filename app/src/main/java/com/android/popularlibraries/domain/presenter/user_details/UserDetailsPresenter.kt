package com.android.popularlibraries.domain.presenter.user_details

import com.android.popularlibraries.domain.model.GithubUser
import com.android.popularlibraries.view.IUserDetailsView
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