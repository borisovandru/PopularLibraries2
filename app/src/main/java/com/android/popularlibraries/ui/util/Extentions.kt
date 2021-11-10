package com.android.popularlibraries.ui.util

import androidx.appcompat.app.AppCompatActivity
import moxy.MvpAppCompatFragment

fun MvpAppCompatFragment.setToolbarTitle(text: String) {
    (requireActivity() as AppCompatActivity).supportActionBar?.title = text
}