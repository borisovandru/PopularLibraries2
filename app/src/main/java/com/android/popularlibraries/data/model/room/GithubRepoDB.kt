package com.android.popularlibraries.data.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "github_user_repos",
    foreignKeys = [
        ForeignKey(
            entity = GithubUserDB::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class GithubRepoDB(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "user_id")
    val userId: Int,
    val name: String,
    @ColumnInfo(name = "private")
    val isPrivate: Boolean,
    val forks: Int,
    val description: String
)