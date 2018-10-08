package com.gotu.exercise

import android.content.Intent
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gotu.exercise.api.Person
import kotlinx.android.synthetic.main.person_list_content.view.*

class PersonListPresenter {

    fun setupRecyclerView(supportFragmentManager: FragmentManager, recyclerView: RecyclerView, results: List<Person>) {
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(supportFragmentManager, results)
    }

    class SimpleItemRecyclerViewAdapter(private val supportFragmentManager: FragmentManager,
                                        private val values: List<Person>) :
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

}