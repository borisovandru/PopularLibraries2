package com.android.popularlibraries.presentation.presenter.user_list

import com.android.popularlibraries.presentation.view.fragments.user_list.adapter.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}