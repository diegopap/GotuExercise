package com.gotu.exercise.splash

import android.os.Bundle
import com.gotu.exercise.R
import com.gotu.exercise.api.User
import com.gotu.exercise.list.UserListContract
import com.gotu.exercise.utils.openList
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity(), UserListContract.View  {
    @Inject
    lateinit var presenter : UserListContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
        presenter.loadUsers()
    }

    override fun onPause() {
        super.onPause()
        presenter.dropView()
    }

    override fun showUserList(users: List<User>) {
        openList(this,  users as ArrayList<User>)
        finish()
    }

}
