package com.gotu.exercise.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gotu.exercise.R
import com.gotu.exercise.api.User
import kotlinx.android.synthetic.main.user_detail.view.*

/**
 * A fragment representing a single User detail screen.
 * This fragment is either contained in a [UserListActivity]
 * in two-pane mode (on tablets) or a [UserDetailActivity]
 * on handsets.
 */
class UserDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.user_detail, container, false)

        val item = arguments?.getSerializable(ARG_ITEM) as User

        // Show the dummy content as text in a TextView.
        item.let {
            rootView.name.text = "${item.name.title.capitalize()} ${item.name.first.capitalize()} ${item.name.last.capitalize()}"
            rootView.email.text = it.email
        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM = "item"
    }
}
