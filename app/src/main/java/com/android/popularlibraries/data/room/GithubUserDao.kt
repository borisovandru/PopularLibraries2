package com.android.popularlibraries.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.android.popularlibraries.data.model.room.GithubUserDB
import io.reactivex.Single

@Dao
interface GithubUserDao {
    @Query("SELECT * FROM github_users")
    fun getAllUsers(): Single<List<GithubUserDB>>

    @Insert(onConflict = IGNORE)
    fun saveUser(user: GithubUserDB)

    @Insert(onConflict = REPLACE)
    fun saveUsers(users: List<GithubUserDB>)

    @Delete
    fun removeUser(user: GithubUserDB)
}