package com.android.popularlibraries.ui.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UserDetailView : MvpView {
    fun init()
    fun showUserLogin(login: String)
}