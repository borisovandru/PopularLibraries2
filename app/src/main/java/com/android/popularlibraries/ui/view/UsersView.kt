package com.android.popularlibraries.ui.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UsersView : MvpView {

    fun init()
    fun updateList()

    @StateStrategyType(SkipStrategy::class)
    fun showErrorToast(message: String?)
    fun showLoading()
    fun showReload()
    fun showMainContent()
}