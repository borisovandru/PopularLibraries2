package com.android.popularlibraries.presentation.presenter.user_list

import com.android.popularlibraries.data.model.GithubUser
import com.android.popularlibraries.presentation.view.fragments.user_list.adapter.IUserItemView

class UserListPresenter : IUserListPresenter {

    val users = mutableListOf<GithubUser>()

    override var itemClickListener: ((IUserItemView) -> Unit)? = null

    override fun getCount() = users.size

    override fun bindView(view: IUserItemView) {
        val user = users[view.pos]
        view.setLogin(user.login)
    }
}