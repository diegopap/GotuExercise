package com.gotu.exercise.list

import android.util.Log
import com.gotu.exercise.api.RandomUserService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserListPresenter @Inject constructor() : UserListContract.Presenter {
    @Inject
    lateinit var randomUserService: RandomUserService

    private var userListView : UserListContract.View? = null

    override fun takeView(view : UserListContract.View) {
        userListView = view
    }

    override fun loadUsers() {
        randomUserService.getRandomUsers(1, 50, "test")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            Log.d("results", "total:" + result.results.size)
                            userListView?.showUserList(result.results)
                        },
                        { error ->
                            Log.d("error", error.message) }
                )
    }

    override fun dropView() {
        userListView = null
    }

}