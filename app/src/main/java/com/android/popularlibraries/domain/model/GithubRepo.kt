package com.android.popularlibraries.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubRepo(
    val id: Int,
    val userId: Int,
    val name: String,
    val private: Boolean,
    val forks: Int,
    val description: String?
) : Parcelable