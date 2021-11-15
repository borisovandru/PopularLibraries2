package com.android.popularlibraries

import android.app.Application
import androidx.room.Room
import com.android.popularlibraries.data.domain.EventBus
import com.android.popularlibraries.data.datasource.GitHubApi
import com.android.popularlibraries.data.room.GithubDatabase
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_URL = "https://api.github.com"
const val DB_NAME = "githubDB"

class App : Application() {

    //Временно до даггера положим это тут
    // навигация
    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

    // rx
    val eventBus = EventBus


    //okhttp3
    private val okClient = OkHttpClient.Builder()
        .addNetworkInterceptor(StethoInterceptor())
        .build()

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }

    // репозиторий
    //api
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val api: GitHubApi by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(GitHubApi::class.java)
    }

    //room
    val gitHubDB
        get() = Room
            .databaseBuilder(this, GithubDatabase::class.java, DB_NAME)
            .build()

}