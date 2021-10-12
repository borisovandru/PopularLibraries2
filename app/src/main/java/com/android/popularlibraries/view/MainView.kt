package com.android.popularlibraries.view

import com.android.popularlibraries.presenter.ButtonType

interface MainView {
    fun setButtonText(index: ButtonType, text: String)
}
