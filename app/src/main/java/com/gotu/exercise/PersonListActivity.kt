package com.gotu.exercise

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gotu.exercise.api.Person
import kotlinx.android.synthetic.main.activity_person_list.*
import kotlinx.android.synthetic.main.person_list.*
import kotlinx.android.synthetic.main.person_list_content.view.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [PersonDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class PersonListActivity : AppCompatActivity(), PersonListContract.View {

    private var presenter : PersonListContract.Presenter = PersonListPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        presenter.setView(this)
        presenter.loadUsers()
    }

    override fun showPersonList(persons: List<Person>) {
        person_list.adapter = SimpleItemRecyclerViewAdapter(persons)
    }

    class SimpleItemRecyclerViewAdapter(private val values: List<Person>) :
            RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                val item = v.tag as Person
                val intent = Intent(v.context, PersonDetailActivity::class.java).apply {
                    putExtra(PersonDetailFragment.ARG_ITEM, item)
                }
                v.context.startActivity(intent)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.person_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            holder.idView.text = position.toString()
            holder.contentView.text = item.email

            with(holder.itemView) {
                tag = item
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val idView: TextView = view.id_text
            val contentView: TextView = view.content
        }
    }

    override fun setLoadingIndicator(active: Boolean) {
        TODO("not implemented")
    }


}
