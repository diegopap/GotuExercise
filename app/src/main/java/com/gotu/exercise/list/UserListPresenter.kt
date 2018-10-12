package com.gotu.exercise.list

import android.util.Log
import com.gotu.exercise.api.RandomUserService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserListPresenter @Inject constructor() : UserListContract.Presenter {
    @Inject
    lateinit var randomUserService: RandomUserService

    private var userListView : UserListContract.View? = null

    private var page = 1

    private val disposable = CompositeDisposable()

    override fun takeView(view : UserListContract.View) {
        userListView = view
    }

    override fun loadUsers() {
        disposable.add(randomUserService.getRandomUsers(page, 50, "test")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            Log.d("results", "total:" + result.results.size)
                            userListView?.showUserList(result.results)
                        },
                        { error ->
                            Log.d("error", error.message) }
                ))
        page++
    }

    override fun dropView() {
        userListView = null
        disposable.clear()
    }

}