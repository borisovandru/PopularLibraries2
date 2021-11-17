package com.android.popularlibraries.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.popularlibraries.R
import com.android.popularlibraries.databinding.FragmentUsersBinding
import com.android.popularlibraries.ui.common.BackButtonListener
import com.android.popularlibraries.ui.utils.app
import com.android.popularlibraries.ui.utils.errorMessage
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter().apply {
            requireActivity().app.appComponent.inject(this)
        }
    }

    private var adapter: UsersAdapter? = null

    private var vb: FragmentUsersBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.usersRecyclerView?.layoutManager = LinearLayoutManager(context)
        adapter = UsersAdapter(presenter.usersListPresenter)
        vb?.usersRecyclerView?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showProgressBar() {
        vb?.loadingLayout?.progressBar?.isVisible = true
    }

    override fun hideProgressBar() {
        vb?.loadingLayout?.progressBar?.isVisible = false
    }

    override fun showErrorMessage(message: String?) {
        errorMessage(
            context,
            String.format("%s\n%s", getString(R.string.error_loading), message.toString())
        )
    }

    override fun backPressed() = presenter.backPressed()
}