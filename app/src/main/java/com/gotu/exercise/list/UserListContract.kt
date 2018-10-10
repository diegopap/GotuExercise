package com.gotu.exercise.list

import com.gotu.exercise.api.User

interface UserListContract {

  interface View {

    fun showUserList(users: List<User>)

  }

  interface Presenter {

    fun takeView(view: View)

    fun loadUsers()

    fun dropView()

  }
}