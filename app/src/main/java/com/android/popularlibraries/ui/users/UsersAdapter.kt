package com.android.popularlibraries.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.popularlibraries.data.domain.UserItemView
import com.android.popularlibraries.data.domain.UserListPresenter
import com.android.popularlibraries.data.model.GithubUser
import com.android.popularlibraries.databinding.ItemUserBinding
import com.android.popularlibraries.ui.utils.loadInfo

class UsersAdapter(private val presenter: UserListPresenter) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(private val vb: ItemUserBinding) : RecyclerView.ViewHolder(vb.root),
        UserItemView {
        override var pos = -1

        override fun setGitUser(gitHunUser: GithubUser) = with(vb) {
            loginTextView.text = gitHunUser.login.toString()
        }

        override fun imageLoad(url: String?) {
            vb.avatarImageView.loadInfo(url)
        }
    }
}
