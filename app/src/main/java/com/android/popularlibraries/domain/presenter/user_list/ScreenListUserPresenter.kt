package com.android.popularlibraries.domain.presenter.user_list

import com.android.popularlibraries.domain.repository.GithubUsersRepo
import com.android.popularlibraries.ui.screens.IScreens
import com.android.popularlibraries.view.IUserListView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class ScreenUserListPresenter(
    private val githubUsersRepository: GithubUsersRepo,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<IUserListView>() {

    val userListPresenter = UserListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        userListPresenter.itemClickListener = { itemView ->
            val user = userListPresenter.users[itemView.pos]
            router.navigateTo(screens.userDetail(user))
        }
    }

    private fun loadData() {
        val users = githubUsersRepository.getUsers()
        userListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}