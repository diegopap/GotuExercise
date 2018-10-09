package com.gotu.exercise.utils

import android.content.Context
import android.content.Intent
import com.gotu.exercise.api.User
import com.gotu.exercise.detail.UserDetailActivity
import com.gotu.exercise.detail.UserDetailFragment

fun openDetail(context: Context, user: User) {
    val intent = Intent(context, UserDetailActivity::class.java).apply {
        putExtra(UserDetailFragment.ARG_ITEM, user)
    }
    context.startActivity(intent)
}