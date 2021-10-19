package com.android.popularlibraries.ui

import android.os.Bundle
import com.android.popularlibraries.databinding.ActivityMainBinding
import com.android.popularlibraries.model.CountersModel
import com.android.popularlibraries.presenter.MainPresenter
import com.android.popularlibraries.view.IMainView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), IMainView {

    private lateinit var activityMainBinding: ActivityMainBinding
    private val presenter by moxyPresenter { MainPresenter(CountersModel()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.btnCounter1.setOnClickListener{ presenter.setButtonText1()}
        activityMainBinding.btnCounter2.setOnClickListener{ presenter.setButtonText2()}
        activityMainBinding.btnCounter3.setOnClickListener{ presenter.setButtonText3()}
    }

    override fun setButtonText1(text: String) {
        activityMainBinding.btnCounter1.text = text
    }

    override fun setButtonText2(text: String) {
        activityMainBinding.btnCounter2.text = text
    }

    override fun setButtonText3(text: String) {
        activityMainBinding.btnCounter3.text = text
    }

}