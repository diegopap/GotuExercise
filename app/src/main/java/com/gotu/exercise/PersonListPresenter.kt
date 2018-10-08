package com.gotu.exercise

import android.content.Context
import android.util.Log
import com.gotu.exercise.api.Person
import com.gotu.exercise.api.RandomUserService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PersonListPresenter : PersonListContract.Presenter {

    private var personListView : PersonListContract.View? = null

    override fun setView(view : PersonListContract.View) {
        personListView = view
    }

    override fun loadUsers() {
        RandomUserService.instance.getRandomUsers(1, 50, "test")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            Log.d("results", "total:" + result.results.size)
                            personListView?.showPersonList(result.results)
                        },
                        { error -> Log.d("error", error.message) }
                )
    }

    override fun openDetail(context: Context, person: Person) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



}