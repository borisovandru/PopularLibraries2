package com.android.popularlibraries.presentation.presenter.main

import com.android.popularlibraries.presentation.utils.screens.IScreens
import com.android.popularlibraries.presentation.view.activity.main.IMainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<IMainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.openUserListFragment())
    }

    fun backClicked() {
        router.exit()
    }
}