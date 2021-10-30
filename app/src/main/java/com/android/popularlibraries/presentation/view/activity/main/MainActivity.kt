package com.android.popularlibraries.presentation.view.activity.main

import android.os.Bundle
import com.android.popularlibraries.data.app.App
import com.android.popularlibraries.R
import com.android.popularlibraries.databinding.ActivityMainBinding
import com.android.popularlibraries.presentation.presenter.main.MainPresenter
import com.android.popularlibraries.presentation.utils.screens.Screens
import com.android.popularlibraries.presentation.utils.IBackButtonListener
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), IMainView {

    private val navigator = AppNavigator(this, R.id.container)

    private val mainPresenter by moxyPresenter {
        MainPresenter(
            App.instance.router,
            Screens()
        )
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
        mainPresenter.backClicked()
    }
}