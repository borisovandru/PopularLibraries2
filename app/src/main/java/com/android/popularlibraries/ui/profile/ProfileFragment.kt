package com.android.popularlibraries.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.android.popularlibraries.R
import com.android.popularlibraries.databinding.FragmentProfileBinding
import com.android.popularlibraries.data.model.GithubUser
import com.android.popularlibraries.ui.common.BackButtonListener
import com.android.popularlibraries.ui.utils.app
import com.android.popularlibraries.ui.utils.errorMessage
import com.android.popularlibraries.ui.utils.loadInfo
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

const val GITHUB_USER = "GITHUB_USER"

class ProfileFragment : MvpAppCompatFragment(), ProfileView, BackButtonListener {

    companion object {
        fun newInstance(gitHubUser: GithubUser) =
            ProfileFragment().apply { arguments = bundleOf(GITHUB_USER to gitHubUser) }
    }

    private val gitHubUser: GithubUser? by lazy {
        arguments?.getParcelable(GITHUB_USER)
    }
    private val binding: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)
    private val presenter: ProfilePresenter by moxyPresenter {
        ProfilePresenter(
            gitHubUser
        ).apply {
            requireActivity().app.appComponent.inject(this)
        }
    }
    private var adapter: ProfileAdapter? = null
    private var countLike: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.repositoriesRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = ProfileAdapter(presenter)
        binding.repositoriesRecyclerView.adapter = adapter
        binding.likeButton.setOnClickListener {
        }
    }

    override fun backPressed() = presenter.backPressed()

    override fun setUser(user: GithubUser) {
        binding.loginTextView.text = user.login.toString()
        binding.likeButton.isEnabled = user.like
        binding.picImageView.loadInfo(user.avatarUrl)
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun setCountLike() {
        countLike = presenter.setLikeCount(countLike)
        binding.counterTextView.text = countLike.toString()
    }

    override fun openUserRepo(repoUrl: String?) {
        startActivity(Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(repoUrl)
        })
    }

    override fun showProgressBar() {
        binding.loadingLayout.progressBar.isVisible = true
    }

    override fun hideProgressBar() {
        binding.loadingLayout.progressBar.isVisible = false
    }

    override fun showErrorMessage(message: String?) {
        errorMessage(
            context,
            String.format("%s\n%s", getString(R.string.error_profile), message.toString())
        )
    }

}
