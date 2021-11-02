package com.android.popularlibraries.ui.utils

import android.content.Context
import android.widget.Toast
import com.android.popularlibraries.App

fun errorMessage(context: Context?, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

val Context.app: App
    get() {
        return applicationContext as App
    }