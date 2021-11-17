package com.android.popularlibraries.data.di

import com.android.popularlibraries.data.datasource.GitHubApi
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://api.github.com"

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideMoshiBuild(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun provideStethoInterceptor(): OkHttpClient {
        return OkHttpClient.Builder().addNetworkInterceptor(StethoInterceptor()).build()
    }

    @Provides
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): GitHubApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(GitHubApi::class.java)
    }

}