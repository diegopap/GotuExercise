package com.gotu.exercise.utils

import android.content.Context
import android.content.Intent
import com.gotu.exercise.api.User
import com.gotu.exercise.detail.UserDetailActivity
import com.gotu.exercise.list.UserListActivity

const val ARG_ITEM = "item"

const val ARG_ITEMS = "items"

fun openDetail(context: Context, user: User) {
    val intent = Intent(context, UserDetailActivity::class.java).apply {
        putExtra(ARG_ITEM, user)
    }
    context.startActivity(intent)
}

fun openList(context: Context, users: ArrayList<User>) {
    val intent = Intent(context, UserListActivity::class.java).apply {
        putExtra(ARG_ITEMS, users)
    }
    context.startActivity(intent)
}