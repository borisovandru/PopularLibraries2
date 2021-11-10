package com.android.popularlibraries.domain.presenter

import com.android.popularlibraries.domain.model.GithubRepo
import com.android.popularlibraries.ui.view.RepoDetailView
import moxy.MvpPresenter

class RepoDetailPresenter : MvpPresenter<RepoDetailView>() {
    var repo: GithubRepo? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun updateRepoInfo() {
        repo?.let {
            viewState.setRepoName(it.name)
            viewState.setRepoForks(it.forks)
            it.description?.let { it1 -> viewState.setRepoDescription(it1) }
        }
    }

    fun backPressed(): Boolean {
        return true
    }
}