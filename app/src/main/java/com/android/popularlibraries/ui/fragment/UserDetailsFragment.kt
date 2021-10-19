package com.android.popularlibraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.popularlibraries.App
import com.android.popularlibraries.Constant.USER_DETAILS
import com.android.popularlibraries.databinding.FragmentUserDetailsBinding
import com.android.popularlibraries.domain.model.GithubUser
import com.android.popularlibraries.domain.presenter.user_details.UserDetailsPresenter
import com.android.popularlibraries.ui.util.IBackButtonListener
import com.android.popularlibraries.view.IUserDetailsView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserDetailsFragment : MvpAppCompatFragment(), IUserDetailsView, IBackButtonListener {

    companion object {
        fun newInstance(gitHubUser: GithubUser): UserDetailsFragment {

            val args = Bundle().apply { putParcelable(USER_DETAILS, gitHubUser) }
            val fragment = UserDetailsFragment()

            fragment.arguments = args
            return fragment
        }
    }

    private val userDetailsPresenter: UserDetailsPresenter by moxyPresenter {
        UserDetailsPresenter(
            App.instance.router
        )
    }
    private var userDetailsFragmentBinding: FragmentUserDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUserDetailsBinding.inflate(inflater, container, false).also {
        userDetailsFragmentBinding = it
    }.root

    override fun onDestroy() {
        super.onDestroy()
        userDetailsFragmentBinding = null
    }

    override fun init() {
        userDetailsPresenter.user = arguments?.getParcelable(USER_DETAILS)
        userDetailsPresenter.user?.login?.let { showUserLogin(it) }
    }

    override fun showUserLogin(login: String) {
        userDetailsFragmentBinding?.userLogin?.text = login
    }

    override fun backPressed() = userDetailsPresenter.backPressed()
}