package com.android.popularlibraries.ui.common

import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ProgressView {
    /**
     * Показывает прогрессбар
     */
    fun showProgressBar()

    /**
     * Скрывает прогрессбар
     */
    fun hideProgressBar()

    /**
     * Выводим сообщение об ошибке
     */
    fun showErrorMessage(message: String?)
}