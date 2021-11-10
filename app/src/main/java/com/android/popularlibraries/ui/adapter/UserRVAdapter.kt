package com.android.popularlibraries.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.popularlibraries.R
import com.android.popularlibraries.databinding.ItemUserBinding
import com.android.popularlibraries.domain.adapter.UserItemView
import com.android.popularlibraries.domain.presenter.IUserListPresenter
import com.bumptech.glide.Glide

class UserRVAdapter(val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UserRVAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(
                    this
                )
            }
        }
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount(): Int =
        presenter.getCount()

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root), UserItemView {
        override var pos = -1

        override fun setLogin(text: String) {
            binding.tvUserLogin.text = text
        }

        override fun loadAvatar(url: String?) {
            Glide
                .with(binding.root)
                .load(url)
                .placeholder(R.drawable.ic_baseline_browser_not_supported_24)
                .into(binding.userAvatar)
        }
    }
}