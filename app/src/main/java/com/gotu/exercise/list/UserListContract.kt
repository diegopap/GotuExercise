package com.gotu.exercise.list

import com.gotu.exercise.api.User

interface UserListContract {

  interface View {

    fun showUserList(users: List<User>)

    fun setLoadingIndicator(active: Boolean)
  }

  interface Presenter {

    fun setView(view: View)

    fun loadUsers()

  }
}