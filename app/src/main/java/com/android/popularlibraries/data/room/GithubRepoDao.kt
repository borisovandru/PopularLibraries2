package com.android.popularlibraries.data.room

import androidx.room.*
import com.android.popularlibraries.data.model.room.GithubRepoDB
import io.reactivex.Single

@Dao
interface GithubRepoDao {
    @Query("SELECT * FROM github_user_repos WHERE id=:userId")
    fun getUserRepos(userId: Int): Single<List<GithubRepoDB>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveUserRepo(repo: GithubRepoDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveRepos(repos: List<GithubRepoDB>)

    @Delete
    fun removeRepo(repo: GithubRepoDB)
}