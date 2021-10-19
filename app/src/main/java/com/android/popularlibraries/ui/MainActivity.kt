package com.android.popularlibraries.ui

import android.os.Bundle
import com.android.popularlibraries.App
import com.android.popularlibraries.R
import com.android.popularlibraries.databinding.ActivityMainBinding
import com.android.popularlibraries.domain.presenter.main.MainPresenter
import com.android.popularlibraries.ui.screens.Screens
import com.android.popularlibraries.ui.util.IBackButtonListener
import com.android.popularlibraries.view.IMainView
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), IMainView {

    private val navigator = AppNavigator(
        this, R.id.container
    )
    private lateinit var activityMainBinding: ActivityMainBinding

    private val presenter by moxyPresenter { MainPresenter(App.instance.router, Screens()) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is IBackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }
}