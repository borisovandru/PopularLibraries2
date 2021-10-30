package com.android.imageconverter.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import com.android.imageconverter.App
import com.android.imageconverter.databinding.ActivityMainBinding
import com.android.imageconverter.domain.presenter.MainPresenter
import com.android.imageconverter.ui.view.MainView

private const val IMAGE_FILE_NAME = "/Curiosity_middle"

class MainActivity : MvpAppCompatActivity(), MainView {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mainPresenter by moxyPresenter { MainPresenter(App.instance.mainRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initListeners()
    }

    @SuppressLint("NewApi")
    private fun initListeners() {
        binding.run {
            btnConvertJpgPng.setOnClickListener {
                mainPresenter.convertJpgToPng(dataDir.absolutePath + IMAGE_FILE_NAME)
            }
            btnCancel.setOnClickListener {
                mainPresenter.cancelAllJobs()
            }
        }
    }

    override fun init() {
        hideInfoBlock()
    }

    override fun showInfoBlock() {
        binding.loadingGroup.visibility = View.VISIBLE
    }

    override fun hideInfoBlock() {
        binding.loadingGroup.visibility = View.GONE
    }

    override fun setLoadProgress(progress: Int) {
        binding.loadingProgress.progress = progress
    }

    override fun setStageText(text: String) {
        binding.convertStage.text = text
    }

    override fun showErrorToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun isEnableConvertButton(isEnabled: Boolean) {
        binding.btnConvertJpgPng.isEnabled = isEnabled
    }
}