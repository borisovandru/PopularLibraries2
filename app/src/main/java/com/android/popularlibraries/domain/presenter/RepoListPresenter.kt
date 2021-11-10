package com.android.popularlibraries.domain.presenter

import com.android.popularlibraries.domain.adapter.RepoItemView
import com.android.popularlibraries.domain.model.GithubRepo

class RepoListPresenter : IRepoListPresenter {
    val repos = mutableListOf<GithubRepo>()
    override var itemClickListener: ((RepoItemView) -> Unit)? = null

    override fun bindView(view: RepoItemView) =
        view.setName(repos[view.pos].name)

    override fun getCount(): Int =
        repos.size
}