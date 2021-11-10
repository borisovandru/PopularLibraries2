package com.android.popularlibraries.domain.presenter

import com.android.popularlibraries.domain.adapter.UserItemView
import com.android.popularlibraries.domain.model.GithubUser

class UserListPresenter : IUserListPresenter {
    val users = mutableListOf<GithubUser>()

    override var itemClickListener: ((UserItemView) -> Unit)? = null

    override fun bindView(view: UserItemView) {
        val githubUser = users[view.pos]
        view.run {
            setLogin(githubUser.login)
            loadAvatar(githubUser.avatar)
        }
    }

    override fun getCount(): Int =
        users.size
}