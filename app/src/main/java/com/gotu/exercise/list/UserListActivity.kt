package com.gotu.exercise.list

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.gotu.exercise.R
import com.gotu.exercise.api.User
import com.gotu.exercise.utils.openDetail
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_user_list.*
import kotlinx.android.synthetic.main.user_list.*
import kotlinx.android.synthetic.main.user_list_content.view.*
import javax.inject.Inject

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [UserDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class UserListActivity : DaggerAppCompatActivity(), UserListContract.View {

    @Inject
    lateinit var presenter : UserListContract.Presenter

    private val visibleThreshold = 6

    private var loading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        person_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItem = (recyclerView.layoutManager as GridLayoutManager).itemCount
                val lastVisibleItem = (recyclerView.layoutManager as GridLayoutManager).findLastVisibleItemPosition()

                if (!loading && lastVisibleItem >= totalItem - visibleThreshold) {
                    loading = true
                    presenter.loadUsers()
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
        presenter.loadUsers()
    }

    override fun showUserList(users: List<User>) {
        loading = false
        if (person_list.adapter == null) {
            person_list.adapter = SimpleItemRecyclerViewAdapter(users as ArrayList<User>)
        } else {
            (person_list.adapter as SimpleItemRecyclerViewAdapter).addAll(users)
        }
    }

    class SimpleItemRecyclerViewAdapter(private val values: ArrayList<User>) :
            RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                openDetail(v.context, v.tag as User)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.user_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]

            Glide.with(holder.image.context).load(item.picture.large).into(holder.image)

            holder.name.text = item.name.toString()

            with(holder.itemView) {
                tag = item
                setOnClickListener(onClickListener)
            }
            val traslationX = Math.random() * 1000f
            val traslationY = Math.random() * 1000f
            val traslationZ = Math.random() * 1000f
            val rotation = Math.random() * 1000f
            holder.itemView.animate()
                    .translationXBy(traslationX.toFloat())
                    .translationYBy(traslationY.toFloat())
                    .translationZBy(traslationZ.toFloat())
                    .rotationBy(rotation.toFloat())
                    .setDuration(0)
                    .withEndAction {
                        holder.itemView.animate()
                            .translationXBy(-traslationX.toFloat())
                            .translationYBy(-traslationY.toFloat())
                            .translationZBy(-traslationZ.toFloat())
                            .rotationBy(-rotation.toFloat())
                            .setDuration(1000)
                            .start()
                    }
                    .start()
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val image: ImageView = view.image
            val name: TextView = view.name
        }

        fun addAll(users : List<User>) {
            values.addAll(users)
            notifyDataSetChanged()
        }
    }

    override fun onPause() {
        super.onPause()
        presenter.dropView()
    }
}
