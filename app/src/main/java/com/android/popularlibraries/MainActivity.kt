package com.android.popularlibraries

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.popularlibraries.databinding.ActivityMainBinding
import com.android.popularlibraries.presenter.ButtonType
import com.android.popularlibraries.presenter.MainPresenter
import com.android.popularlibraries.view.MainView

class MainActivity : AppCompatActivity(), MainView {

    private var vb: ActivityMainBinding? = null

    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        vb?.btnCounter1?.setOnClickListener { presenter.counterClick(ButtonType.FIRST) }
        vb?.btnCounter2?.setOnClickListener { presenter.counterClick(ButtonType.SECOND) }
        vb?.btnCounter3?.setOnClickListener { presenter.counterClick(ButtonType.THIRD) }
    }

    //Подсказка к ПЗ: поделить на 3 отдельные функции и избавиться от index
    override fun setButtonText(index: ButtonType, text: String) {
        when (index) {
            ButtonType.FIRST -> vb?.btnCounter1?.text = text
            ButtonType.SECOND -> vb?.btnCounter2?.text = text
            ButtonType.THIRD -> vb?.btnCounter3?.text = text
        }
    }
}
