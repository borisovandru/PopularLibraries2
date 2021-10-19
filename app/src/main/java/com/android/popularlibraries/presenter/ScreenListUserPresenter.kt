package com.android.popularlibraries.presenter

import com.android.popularlibraries.model.GithubUser
import com.android.popularlibraries.model.GithubUsersRepo
import com.android.popularlibraries.view.IListUsersView
import com.android.popularlibraries.view.IUserItemView
import moxy.MvpPresenter

class ScreenListUserPresenter(private val usersRepo: GithubUsersRepo) :
    MvpPresenter<IListUsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((IUserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    private val usersListPresenter = UsersListPresenter()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            //TODO: переход на экран пользователя
        }
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }
}