package com.gotu.exercise.detail

import android.content.Context
import android.content.Intent
import android.util.Log
import com.gotu.exercise.api.RandomUserService
import com.gotu.exercise.api.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserDetailPresenter : UserDetailContract.Presenter {

    private var userDetailView : UserDetailContract.View? = null

    override fun setView(view: UserDetailContract.View) {
        userDetailView = view
    }

    override fun openDetail(context: Context, user: User) {
        val intent = Intent(context, UserDetailActivity::class.java).apply {
            putExtra(UserDetailFragment.ARG_ITEM, user)
        }
        context.startActivity(intent)
    }

    override fun getFriends() {
        val friend1 = RandomUserService.instance.getRandomUsers(1, 1, "friend1")
        val friend2 = RandomUserService.instance.getRandomUsers(1, 1, "friend2")
        friend1.mergeWith(friend2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            Log.d("results", "total:" + result.results.size)
                            userDetailView?.showFriends(result.results)
                        },
                        { error -> Log.d("error", error.message) }
                )
        }

}
