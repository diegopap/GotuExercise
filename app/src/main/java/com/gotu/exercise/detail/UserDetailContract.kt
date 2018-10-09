package com.gotu.exercise.detail

import com.gotu.exercise.api.User

interface UserDetailContract {

  interface View {

    fun showFriends(users: List<User>)

    fun setLoadingIndicator(active: Boolean)
  }

  interface Presenter {

    fun setView(view: View)

    fun getFriends(seed: String)

  }
}