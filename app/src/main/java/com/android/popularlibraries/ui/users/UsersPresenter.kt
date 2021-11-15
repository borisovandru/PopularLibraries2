package com.android.popularlibraries.ui.users

import com.android.popularlibraries.AndroidScreens
import com.android.popularlibraries.App
import com.android.popularlibraries.SchedulerProvider
import com.android.popularlibraries.data.domain.AppState
import com.android.popularlibraries.data.domain.NetworkStatusImpl
import com.android.popularlibraries.data.domain.UserItemView
import com.android.popularlibraries.data.domain.UserListPresenter
import com.android.popularlibraries.data.model.GithubUser
import com.android.popularlibraries.data.repository.GithubUserRepoCombinedImpl
import com.android.popularlibraries.data.repository.GithubUsersLocalRepoImpl
import com.android.popularlibraries.data.repository.GithubUsersWebRepoImpl
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class UsersPresenter(app: App) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : UserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.let {
                view.setGitUser(it)
                view.imageLoad(user.avatarUrl)
            }
        }
    }

    private val schedulerProvider: SchedulerProvider = SchedulerProvider()
    private val usersRepo = GithubUserRepoCombinedImpl(
        GithubUsersLocalRepoImpl(app.gitHubDB),
        GithubUsersWebRepoImpl(app.api),
        NetworkStatusImpl(app),
        schedulerProvider
    )
    private val router = app.router

    val usersListPresenter = UsersListPresenter()
    private var currentDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.showProgressBar()
        loadData()
    }

    private fun loadData() {
        currentDisposable.add(usersRepo.githubUsers()
            .observeOn(schedulerProvider.ui())
            .subscribe(
                { userList -> renderData(AppState.Success(userList)) },
                { error -> renderData(AppState.Error(error)) }
            ))
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                appState.data.let { dataUsers ->
                    usersListPresenter.users.addAll(dataUsers)
                    viewState.hideProgressBar()
                    usersListPresenter.itemClickListener = { itemView ->
                        dataUsers[itemView.pos].let {
                            router.navigateTo(AndroidScreens().profile(it))
                        }
                    }
                    viewState.updateList()
                }
            }
            is AppState.Loading -> {
                viewState.showProgressBar()
            }
            is AppState.Error -> {
                viewState.showErrorMessage(appState.error.message)
            }
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        currentDisposable.dispose()
        super.onDestroy()
    }
}