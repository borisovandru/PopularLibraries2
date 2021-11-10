package com.android.popularlibraries.domain.presenter

import com.android.popularlibraries.ui.screen.IScreens
import com.android.popularlibraries.ui.view.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}