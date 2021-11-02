package com.android.popularlibraries.ui.users

import com.android.popularlibraries.ui.common.ProgressView
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UsersView : MvpView, ProgressView {
    fun init()
    fun updateList()
}