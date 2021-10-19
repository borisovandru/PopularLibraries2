package com.android.popularlibraries.domain.presenter.user_list

import com.android.popularlibraries.domain.model.GithubUser
import com.android.popularlibraries.view.IUserItemView

class UserListPresenter : IUserListPresenter {

    val users = mutableListOf<GithubUser>()

    override var itemClickListener: ((IUserItemView) -> Unit)? = null

    override fun getCount() = users.size

    override fun bindView(view: IUserItemView) {
        val user = users[view.pos]
        view.setLogin(user.login)
    }
}