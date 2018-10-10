package com.gotu.exercise.detail

import com.gotu.exercise.api.User

interface UserDetailContract {

  interface View {

    fun showFriends(users: List<User>)

  }

  interface Presenter {

    fun takeView(view: View)

    fun getFriends(seed: String)

    fun dropView()

  }
}