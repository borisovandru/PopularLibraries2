package com.android.popularlibraries.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    val id: Int,
    val login: String,
    val avatar: String?,
    val reposUrl: String
) : Parcelable