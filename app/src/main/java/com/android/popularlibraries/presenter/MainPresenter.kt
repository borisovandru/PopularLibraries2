package com.android.popularlibraries.presenter

import com.android.popularlibraries.model.CountersModel
import com.android.popularlibraries.view.MainView

class MainPresenter(private val view: MainView) {

    private val model = CountersModel()

    //Архитектурная ошибка. В качестве практического задания -- исправить
    fun counterClick(type: ButtonType) {
        when (type) {
            ButtonType.FIRST -> {
                val nextValue = model.next(0)
                view.setButtonText(ButtonType.FIRST, nextValue.toString())
            }
            ButtonType.SECOND,
            -> {
                val nextValue = model.next(1)
                view.setButtonText(ButtonType.SECOND, nextValue.toString())
            }
            ButtonType.THIRD -> {
                val nextValue = model.next(2)
                view.setButtonText(ButtonType.THIRD, nextValue.toString())
            }
        }
    }
}

enum class ButtonType {
    FIRST,
    SECOND,
    THIRD
}
