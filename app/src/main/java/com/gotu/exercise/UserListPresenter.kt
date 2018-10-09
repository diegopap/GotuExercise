package com.gotu.exercise

import android.content.Context
import android.content.Intent
import android.util.Log
import com.gotu.exercise.api.RandomUserService
import com.gotu.exercise.api.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserListPresenter : UserListContract.Presenter {

    private var userListView : UserListContract.View? = null

    override fun setView(view : UserListContract.View) {
        userListView = view
    }

    override fun loadUsers() {
        RandomUserService.instance.getRandomUsers(1, 50, "test")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            Log.d("results", "total:" + result.results.size)
                            userListView?.showUserList(result.results)
                        },
                        { error -> Log.d("error", error.message) }
                )
    }

    override fun openDetail(context: Context, user: User) {
        val intent = Intent(context, UserDetailActivity::class.java).apply {
            putExtra(UserDetailFragment.ARG_ITEM, user)
        }
        context.startActivity(intent)
    }
}