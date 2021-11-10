package com.android.popularlibraries.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.popularlibraries.App
import com.android.popularlibraries.R
import com.android.popularlibraries.databinding.FragmentUsersBinding
import com.android.popularlibraries.domain.presenter.UsersPresenter
import com.android.popularlibraries.ui.adapter.UserRVAdapter
import com.android.popularlibraries.ui.util.BackButtonListener
import com.android.popularlibraries.ui.util.setToolbarTitle
import com.android.popularlibraries.ui.view.UsersView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance(): UsersFragment = UsersFragment().apply {
            App.instance.appComponent.inject(this)
        }
    }

    private var _binding: FragmentUsersBinding? = null
    private val binding: FragmentUsersBinding
        get() = checkNotNull(_binding) { getString(R.string.binding_create_error, this::class) }

    private val presenter by moxyPresenter {
        UsersPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    private val userRVAdapter: UserRVAdapter by lazy {
        UserRVAdapter(presenter.userListPresenter)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun init() {
        setToolbarTitle(getString(R.string.app_name))
        initRV()
        initListeners()
    }

    private fun initListeners() {
        binding.btnReload.setOnClickListener {
            presenter.reloadData()
        }
    }

    private fun initRV() {
        binding.rvUsers.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = userRVAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        userRVAdapter.notifyDataSetChanged()
    }

    override fun showErrorToast(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        binding.run {
            loadingIndicator.visibility = View.VISIBLE
            rvUsers.visibility = View.GONE
            btnReload.visibility = View.GONE
        }
    }

    override fun showReload() {
        binding.run {
            loadingIndicator.visibility = View.GONE
            rvUsers.visibility = View.GONE
            btnReload.visibility = View.VISIBLE
        }
    }

    override fun showMainContent() {
        binding.run {
            loadingIndicator.visibility = View.GONE
            rvUsers.visibility = View.VISIBLE
            btnReload.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onBackPressed(): Boolean =
        presenter.backPressed()
}