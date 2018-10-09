package com.gotu.exercise.detail

import android.util.Log
import com.gotu.exercise.api.RandomUserService
import com.gotu.exercise.api.Response
import com.gotu.exercise.api.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class UserDetailPresenter : UserDetailContract.Presenter {

    private var userDetailView : UserDetailContract.View? = null

    override fun setView(view: UserDetailContract.View) {
        userDetailView = view
    }

    override fun getFriends(seed: String) {
        val friend1 = RandomUserService.instance.getRandomUsers(1, 1, seed + "1")
        val friend2 = RandomUserService.instance.getRandomUsers(1, 1, seed + "2")
        friend1.zipWith(friend2, BiFunction<Response, Response, Response> { r1, r2 -> convert(r1,r2)})
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

    fun convert(response1 : Response, response2 : Response) : Response {
        val users = response1.results as ArrayList<User>
        users.addAll(response2.results)
        return Response(users)
    }

}
