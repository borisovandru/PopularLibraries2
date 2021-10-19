package com.android.popularlibraries.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.popularlibraries.App
import com.android.popularlibraries.databinding.FragmentUserListBinding
import com.android.popularlibraries.domain.presenter.user_list.ScreenUserListPresenter
import com.android.popularlibraries.domain.repository.GithubUsersRepo
import com.android.popularlibraries.ui.adapter.UsersRVAdapter
import com.android.popularlibraries.ui.screens.Screens
import com.android.popularlibraries.ui.util.IBackButtonListener
import com.android.popularlibraries.view.IUserListView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserListFragment : MvpAppCompatFragment(), IUserListView, IBackButtonListener {

    companion object {
        fun newInstance() = UserListFragment()
    }

    private val screenUserListPresenter: ScreenUserListPresenter by moxyPresenter {
        ScreenUserListPresenter(GithubUsersRepo(), App.instance.router, Screens())
    }

    private var adapter: UsersRVAdapter? = null
    private var fragmentUserListBinding: FragmentUserListBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUserListBinding.inflate(inflater, container, false).also {
        fragmentUserListBinding = it
    }.root

    override fun onDestroy() {
        super.onDestroy()
        fragmentUserListBinding = null
    }

    override fun init() {
        fragmentUserListBinding?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(screenUserListPresenter.userListPresenter)
        fragmentUserListBinding?.rvUsers?.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = screenUserListPresenter.backPressed()
}