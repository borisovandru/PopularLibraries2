package com.android.popularlibraries.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IMainView : MvpView {
    fun setButtonText1(text: String)
    fun setButtonText2(text: String)
    fun setButtonText3(text: String)
}
