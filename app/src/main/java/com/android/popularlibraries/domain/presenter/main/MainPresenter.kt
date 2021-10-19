package com.android.popularlibraries.domain.presenter.main

import com.android.popularlibraries.ui.screens.IScreens
import com.android.popularlibraries.view.IMainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(private val router: Router, private val screen: IScreens) :
    MvpPresenter<IMainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screen.userList())
    }

    fun backClicked() {
        router.exit()
    }
}
