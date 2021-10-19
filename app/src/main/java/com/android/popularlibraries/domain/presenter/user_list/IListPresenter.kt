package com.android.popularlibraries.domain.presenter.user_list

import com.android.popularlibraries.view.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}