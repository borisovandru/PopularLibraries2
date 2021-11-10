package com.android.popularlibraries.domain.presenter

import com.android.popularlibraries.domain.model.GithubUser
import com.android.popularlibraries.ui.view.UserDetailView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class UserDetailPresenter : MvpPresenter<UserDetailView>() {
    var user: GithubUser? = null

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}