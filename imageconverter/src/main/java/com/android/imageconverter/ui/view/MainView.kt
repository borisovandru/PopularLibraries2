package com.android.imageconverter.ui.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {
    fun init()
    fun showInfoBlock()
    fun hideInfoBlock()
    fun setLoadProgress(progress: Int)
    fun setStageText(text: String)
    fun showErrorToast(message: String)
    fun isEnableConvertButton(isEnabled: Boolean)

}