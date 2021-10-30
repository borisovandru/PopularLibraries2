package com.android.popularlibraries.presentation.view.fragments.user_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.popularlibraries.data.app.App
import com.android.popularlibraries.data.model.GithubUser
import com.android.popularlibraries.databinding.FragmentUserDetailsBinding
import com.android.popularlibraries.presentation.presenter.user_details.UserDetailsPresenter
import com.android.popularlibraries.presentation.utils.IBackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserDetailsFragment : MvpAppCompatFragment(), IUserDetailsView, IBackButtonListener {

    companion object {

        const val USER = "User"

        fun newInstance(user: GithubUser): UserDetailsFragment {

            val args = Bundle().apply { putParcelable(USER, user) }
            val fragment = UserDetailsFragment()

            fragment.arguments = args
            return fragment
        }
    }

    private val detailsPresenter by moxyPresenter { UserDetailsPresenter(App.instance.router) }

    private var binding: FragmentUserDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUserDetailsBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun init() {
        detailsPresenter.user = arguments?.getParcelable(USER)
        detailsPresenter.user?.login?.let { showUserLogin(it) }
    }

    override fun showUserLogin(login: String) {
        binding?.loginUserDetails?.text = login
    }

    override fun backPressed() = detailsPresenter.backPressed()

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}