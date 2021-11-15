package com.android.popularlibraries.data.domain

import com.android.popularlibraries.data.model.GithubUser

sealed class AppState {

    data class Success(val data: List<GithubUser>) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}