package com.android.popularlibraries.data.repository

import com.android.popularlibraries.data.GithubUser
import com.android.popularlibraries.data.UsersRepository
import com.android.popularlibraries.data.datasource.GitHubApi
import com.android.popularlibraries.SchedulerProvider
import io.reactivex.rxjava3.core.Single

class GithubUsersRepoImpl(
    private val githubApi: GitHubApi,
    private val schedulerProvider: SchedulerProvider
) : GithubUsersRepo {

    override fun githubUsers(): Single<List<GithubUser>> =
        githubApi.getGitHubUsers().subscribeOn(schedulerProvider.io())

    override fun userRepos(repoUrl: String): Single<List<UsersRepository>> =
        githubApi.getUserRepos(repoUrl).subscribeOn(schedulerProvider.io())

    override fun githubUser(login: String): Single<GithubUser> =
        githubApi.getUserByLogin(login).subscribeOn(schedulerProvider.io())

}