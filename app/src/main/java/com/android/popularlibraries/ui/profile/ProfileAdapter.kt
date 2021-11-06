package com.android.popularlibraries.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.popularlibraries.data.model.UsersRepository
import com.android.popularlibraries.databinding.ItemRepositoriesBinding
import com.android.popularlibraries.ui.utils.setTint

class ProfileAdapter(private val presenter: ProfilePresenter) :
    RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    private val repoList = presenter.userRepoList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemRepositoriesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(repoList[position])

    override fun getItemCount() = repoList.size

    inner class ViewHolder(private val vb: ItemRepositoriesBinding) :
        RecyclerView.ViewHolder(vb.root) {
        //перепишем при помощи функции расширения
        fun bind(userRepository: UsersRepository) {
            with(vb) {
                itemView.apply {
                    nameTextView.text = userRepository.name.toString()
                    descriptionTextView.text = userRepository.description.toString()
                    imageViewIngBtnAddToFavorites.setTint(userRepository.likeCounter - 1)
                    imageViewIngBtnAddToFavorites.setOnClickListener {
                        userRepository.likeCounter =
                            imageViewIngBtnAddToFavorites.setTint(userRepository.likeCounter)
                        presenter.onLikeClick(userRepository.likeCounter)
                    }
                }
                itemView.setOnClickListener {
                    presenter.openUserRepo(userRepository.htmlUrl)
                }
            }

        }
    }
}