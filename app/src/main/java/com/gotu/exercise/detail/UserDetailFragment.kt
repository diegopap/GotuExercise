package com.gotu.exercise.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gotu.exercise.R
import com.gotu.exercise.api.User
import com.gotu.exercise.utils.ARG_ITEM
import com.gotu.exercise.utils.openDetail
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.user_detail.view.*
import javax.inject.Inject

/**
 * A fragment representing a single User detail screen.
 * This fragment is either contained in a [UserListActivity]
 * in two-pane mode (on tablets) or a [UserDetailActivity]
 * on handsets.
 */
class UserDetailFragment : DaggerFragment() , UserDetailContract.View {

    @Inject
    lateinit var presenter : UserDetailContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.user_detail, container, false)

        val item = arguments?.getSerializable(ARG_ITEM) as User

        rootView.name.text = item.name.toString()
        rootView.email.text = item.email

        return rootView
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
        val item = arguments?.getSerializable(ARG_ITEM) as User
        presenter.getFriends(item.email)
    }


    override fun showFriends(users: List<User>) {
        view?.friend1?.text = users[0].name.toString()
        view?.friend1?.setOnClickListener {
            openDetail(requireContext(), users[0])
        }
        view?.friend2?.text = users[1].name.toString()
        view?.friend2?.setOnClickListener {
            openDetail(requireContext(), users[1])
        }
    }

    override fun onPause() {
        super.onPause()
        presenter.dropView()
    }
}
