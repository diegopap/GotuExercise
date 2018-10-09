package com.gotu.exercise.utils

import android.app.ProgressDialog
import android.content.Context

var progressDialog : ProgressDialog? = null

fun setLoadingIndicator(context: Context, active: Boolean) {
    if (active) {
        progressDialog = ProgressDialog.show(context,"","")
    } else {
        progressDialog?.hide()
    }
}