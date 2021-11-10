package com.android.popularlibraries.domain.adapter

interface UserItemView : IItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String?)
}