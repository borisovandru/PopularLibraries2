package com.android.popularlibraries.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.popularlibraries.data.model.room.GithubRepoDB
import com.android.popularlibraries.data.model.room.GithubUserDB

@Database(entities = [GithubUserDB::class, GithubRepoDB::class], version = 1)
abstract class GithubDatabase : RoomDatabase() {

    abstract fun userDao(): GithubUserDao
    abstract fun repoDao(): GithubRepoDao
}