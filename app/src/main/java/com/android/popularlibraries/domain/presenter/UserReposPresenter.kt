package com.android.popularlibraries.domain.presenter

import com.android.popularlibraries.domain.model.GithubUser
import com.android.popularlibraries.domain.repository.GithubRepository
import com.android.popularlibraries.ui.screen.IScreens
import com.android.popularlibraries.ui.view.UserReposView
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter
import javax.inject.Inject

class UserReposPresenter : MvpPresenter<UserReposView>() {
    var user: GithubUser? = null
    val repoListPresenter = RepoListPresenter()

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var githubRepository: GithubRepository

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadRepos()
        repoListPresenter.itemClickListener = { repoItemView ->
            val githubRepo = repoListPresenter.repos[repoItemView.pos]
            router.navigateTo(screens.repoDetails(githubRepo))
        }
    }

    private fun loadRepos() {
        viewState.showLoading()
        user?.let {
            githubRepository.getUserRepos(it)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        repoListPresenter.repos.addAll(result)
                        viewState.updateList()
                        viewState.showMainContent()
                    },
                    { error ->
                        viewState.showErrorToast(error.message)
                        viewState.showReload()
                    }
                )
        }
    }

    fun backPressed(): Boolean {
        return true
    }

    fun reloadData() {
        loadRepos()
    }
}