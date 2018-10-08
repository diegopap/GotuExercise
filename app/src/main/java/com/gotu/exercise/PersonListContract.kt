package com.gotu.exercise

import android.content.Context
import com.gotu.exercise.api.Person

interface PersonListContract {

  interface View {

    fun showPersonList(persons: List<Person>)

    fun setLoadingIndicator(active: Boolean)
  }

  interface Presenter {

    fun setView(view: View)

    fun loadUsers()

    fun openDetail(context: Context, person: Person)
  }
}