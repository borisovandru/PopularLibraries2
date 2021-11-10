package com.android.popularlibraries.di

import com.android.popularlibraries.domain.presenter.MainPresenter
import com.android.popularlibraries.domain.presenter.UserDetailPresenter
import com.android.popularlibraries.domain.presenter.UserReposPresenter
import com.android.popularlibraries.domain.presenter.UsersPresenter
import com.android.popularlibraries.ui.MainActivity
import com.android.popularlibraries.ui.fragment.UserDetailFragment
import com.android.popularlibraries.ui.fragment.UserReposFragment
import com.android.popularlibraries.ui.fragment.UsersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ResourceModule::class
    ]
)

interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersFragment: UsersFragment)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userDetailPresenter: UserDetailPresenter)
    fun inject(userDetailFragment: UserDetailFragment)
    fun inject(userReposPresenter: UserReposPresenter)
    fun inject(userReposFragment: UserReposFragment)
}