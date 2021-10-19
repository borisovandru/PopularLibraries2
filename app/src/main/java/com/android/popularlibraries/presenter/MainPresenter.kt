package com.android.popularlibraries.presenter

import com.android.popularlibraries.model.CountersModel
import com.android.popularlibraries.view.IMainView
import moxy.MvpPresenter

class MainPresenter(private val model: CountersModel) : MvpPresenter<IMainView>() {

    fun setButtonText1() {
        val nextValue = model.next(0)
        viewState.setButtonText1(nextValue.toString())
    }

    fun setButtonText2() {
        val nextValue = model.next(1)
        viewState.setButtonText2(nextValue.toString())
    }

    fun setButtonText3() {
        val nextValue = model.next(2)
        viewState.setButtonText3(nextValue.toString())
    }
}
