package com.android.popularlibraries.data.repository

import com.android.popularlibraries.data.mapper.toDomain
import com.android.popularlibraries.data.retrofit.GithubService
import com.android.popularlibraries.domain.model.GithubRepo
import com.android.popularlibraries.domain.model.GithubUser
import com.android.popularlibraries.domain.repository.GithubRepository
import com.android.popularlibraries.domain.util.INetworkStatus
import com.android.popularlibraries.domain.util.IRoomGithubCache
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GithubRepositoryWithCacheImpl(
    private val apiService: GithubService,
    private val networkStatus: INetworkStatus,
    private val roomCache: IRoomGithubCache
) : GithubRepository {

    override fun getUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle()
            .flatMap { isOnline ->
                if (isOnline) {
                    apiService.getUsers()
                        .flatMap { users ->
                            Single.fromCallable {
                                roomCache.cacheUsers(users.map { it.toDomain() })
                                users.map { it.toDomain() }
                            }
                        }
                } else {
                    roomCache.getCachedUsers()
                }
            }
            .subscribeOn(Schedulers.io())

    override fun getUserRepos(user: GithubUser): Single<List<GithubRepo>> =
        networkStatus.isOnlineSingle()
            .flatMap { isOnline ->
                if (isOnline) {
                    apiService.getUserRepos(user.reposUrl)
                        .flatMap { repos ->
                            Single.fromCallable {
                                roomCache.cacheRepos(repos.map { it.toDomain() })
                                repos.map { it.toDomain() }
                            }
                        }
                } else {
                    roomCache.getCachedRepos(user.id)
                }
            }
            .subscribeOn(Schedulers.io())
}