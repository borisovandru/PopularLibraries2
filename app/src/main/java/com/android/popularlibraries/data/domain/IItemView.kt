package com.android.popularlibraries.data.domain

import com.android.popularlibraries.data.model.GithubUser

interface IItemView {
    var pos: Int
}

interface UserItemView : IItemView {
    fun setGitUser(gitHunUser: GithubUser)
    fun imageLoad(url: String?)
}