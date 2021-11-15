package com.android.popularlibraries.data.repository

import com.android.popularlibraries.data.datasource.GitHubApi
import com.android.popularlibraries.data.model.GithubUser
import com.android.popularlibraries.data.model.UsersRepository
import io.reactivex.rxjava3.core.Single

class GithubUsersWebRepoImpl(
    private val githubApi: GitHubApi

) : GithubUsersRepo {

    override fun githubUsers(): Single<List<GithubUser>> =
        githubApi.getGitHubUsers()

    override fun userRepos(repoUrl: String, userId:Int?): Single<List<UsersRepository>> =
        githubApi.getUserRepos(repoUrl)

    override fun githubUser(login: String): Single<GithubUser> =
        githubApi.getUserByLogin(login)

}
