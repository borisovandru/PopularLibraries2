package com.android.popularlibraries.ui.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface RepoDetailView : MvpView {
    fun init()
    fun setRepoName(name: String)
    fun setRepoForks(forks: Int)
    fun setRepoDescription(text: String)
}