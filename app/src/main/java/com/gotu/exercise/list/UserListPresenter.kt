package com.gotu.exercise.list

import android.util.Log
import com.gotu.exercise.api.RandomUserService
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

}