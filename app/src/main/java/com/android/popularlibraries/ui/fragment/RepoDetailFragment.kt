package com.android.popularlibraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.popularlibraries.R
import com.android.popularlibraries.databinding.FragmentRepoDetailBinding
import com.android.popularlibraries.domain.model.GithubRepo
import com.android.popularlibraries.domain.presenter.RepoDetailPresenter
import com.android.popularlibraries.ui.util.BackButtonListener
import com.android.popularlibraries.ui.util.setToolbarTitle
import com.android.popularlibraries.ui.view.RepoDetailView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoDetailFragment : MvpAppCompatFragment(), RepoDetailView, BackButtonListener {
    companion object {
        private const val REPO_TAG = "user_tag"
        fun newInstance(repo: GithubRepo): RepoDetailFragment {
            val args = Bundle().apply {
                putParcelable(REPO_TAG, repo)
            }
            val fragment = RepoDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: FragmentRepoDetailBinding? = null
    private val binding: FragmentRepoDetailBinding
        get() = checkNotNull(_binding) { getString(R.string.binding_create_error, this::class) }

    private val presenter by moxyPresenter { RepoDetailPresenter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepoDetailBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun init() {
        presenter.repo = arguments?.getParcelable(REPO_TAG)
        presenter.repo?.name?.let { setToolbarTitle(it) }
        presenter.updateRepoInfo()
    }

    override fun setRepoName(name: String) {
        binding.repoName.text = name
    }

    override fun setRepoForks(forks: Int) {
        binding.repoForks.text = forks.toString()
    }

    override fun setRepoDescription(text: String) {
        binding.repoDescription.text = text
    }

    override fun onBackPressed(): Boolean =
        presenter.backPressed()

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}