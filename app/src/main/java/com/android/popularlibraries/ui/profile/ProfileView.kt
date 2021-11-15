package com.android.popularlibraries.ui.profile

import com.android.popularlibraries.data.model.GithubUser
import com.android.popularlibraries.ui.common.ProgressView
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ProfileView : MvpView, ProgressView {
    fun setUser(user: GithubUser)
    fun updateList()
    fun setCountLike()
    fun openUserRepo(repoUrl: String?)
}