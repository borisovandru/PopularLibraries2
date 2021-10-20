package com.android.popularlibraries.presentation.view.fragments.user_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.popularlibraries.databinding.ItemUserBinding
import com.android.popularlibraries.presentation.presenter.user_list.IUserListPresenter

class UserListAdapter(
    private val mainPresenter: IUserListPresenter
) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root), IUserItemView {

        override var pos = -1
        override fun setLogin(text: String) = with(binding) {
            loginUserList.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ).apply {
            itemView.setOnClickListener { mainPresenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = mainPresenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        mainPresenter.bindView(holder.apply { pos = position })
}