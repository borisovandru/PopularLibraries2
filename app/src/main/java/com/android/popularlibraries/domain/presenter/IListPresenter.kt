package com.android.popularlibraries.domain.presenter

import com.android.popularlibraries.domain.adapter.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}