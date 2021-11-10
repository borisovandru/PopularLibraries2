package com.android.popularlibraries.domain.presenter

import android.annotation.SuppressLint
import com.android.popularlibraries.domain.repository.GithubRepository
import com.android.popularlibraries.ui.screen.IScreens
import com.android.popularlibraries.ui.view.UsersView
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter
import javax.inject.Inject

class UsersPresenter : MvpPresenter<UsersView>() {
    val userListPresenter = UserListPresenter()

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var githubRepository: GithubRepository

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        userListPresenter.itemClickListener = { userItemView ->
            val githubUser = userListPresenter.users[userItemView.pos]
            router.navigateTo(screens.userRepos(githubUser))
        }
    }

    @SuppressLint("CheckResult")
    private fun loadData() {
        viewState.showLoading()
        githubRepository.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    userListPresenter.users.addAll(it)
                    viewState.updateList()
                    viewState.showMainContent()
                },
                {
                    viewState.showErrorToast(it.message)
                    viewState.showReload()
                }
            )
    }

    fun reloadData() {
        loadData()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}