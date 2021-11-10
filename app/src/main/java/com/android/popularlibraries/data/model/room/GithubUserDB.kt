package com.android.popularlibraries.data.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "github_users")
data class GithubUserDB(
    @PrimaryKey val id: Int,
    val login: String,
    val avatar: String,
    @ColumnInfo(name = "repos_url")
    val reposUrl: String
)