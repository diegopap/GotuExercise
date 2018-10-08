package com.gotu.exercise

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.gotu.exercise.api.RandomUserService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_person_list.*
import kotlinx.android.synthetic.main.person_list.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [PersonDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class PersonListActivity : AppCompatActivity() {

    private var presenter = PersonListPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        presenter.setupRecyclerView(supportFragmentManager, person_list)

        RandomUserService.instance.getRandomUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> Log.d("results", "total:" + result.results.size) },
                        { error -> Log.d("error", error.message) }
                )
    }


}
