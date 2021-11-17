package com.android.popularlibraries.data.di

import com.android.popularlibraries.ui.main.MainActivity
import com.android.popularlibraries.ui.main.MainPresenter
import com.android.popularlibraries.ui.profile.ProfilePresenter
import com.android.popularlibraries.ui.users.UsersPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, CiceroneModule::class, ApiModule::class, RepoModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(userPresenter: UsersPresenter)
    fun inject(profilePresenter: ProfilePresenter)
}
