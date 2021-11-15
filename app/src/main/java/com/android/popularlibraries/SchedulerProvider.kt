package com.android.popularlibraries

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class SchedulerProvider {
    fun ui(): Scheduler = AndroidSchedulers.mainThread()
    fun io(): Scheduler = Schedulers.io()
}