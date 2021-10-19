package com.android.popularlibraries.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.popularlibraries.databinding.ItemUserBinding
import com.android.popularlibraries.domain.presenter.user_list.IUserListPresenter
import com.android.popularlibraries.view.IUserItemView

class UsersRVAdapter(private val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(private val itemUserBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemUserBinding.root), IUserItemView {
        override var pos = -1
        override fun setLogin(text: String) = with(itemUserBinding) {
            tvLogin.text = text
        }
    }
}