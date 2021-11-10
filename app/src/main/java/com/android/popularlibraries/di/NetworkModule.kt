package com.android.popularlibraries.di

import android.content.Context
import android.util.Log
import com.android.popularlibraries.data.retrofit.GithubService
import com.android.popularlibraries.domain.util.INetworkStatus
import com.android.popularlibraries.ui.util.NetworkStatus
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Named("base_url")
    fun provideBaseUrl(): String = "https://api.github.com"


    @Singleton
    @Provides
    fun provideRequestService(retrofit: Retrofit): GithubService =
        retrofit.create(GithubService::class.java)


    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        gson: Gson,
        @Named("base_url") baseUrl: String
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create(gson)
            )
            .client(client)
            .build()


    @Provides
    fun provideOkhttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(
                HttpLoggingInterceptor { Log.d("HTTP_LOG", it) }
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            ).build()

    @Provides
    fun provideGson(): Gson =
        GsonBuilder()
            .setLenient()
            .create()

    @Singleton
    @Provides
    fun provideNetworkStatus(context: Context): INetworkStatus =
        NetworkStatus(context)
}