package com.android.popularlibraries.domain.util

import io.reactivex.Observable
import io.reactivex.Single

interface INetworkStatus {
    fun isOnline(): Observable<Boolean>
    fun isOnlineSingle(): Single<Boolean>
}