package com.android.popularlibraries.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface IUserDetailsView : MvpView {
    fun init()
    fun showUserLogin(login: String)
}