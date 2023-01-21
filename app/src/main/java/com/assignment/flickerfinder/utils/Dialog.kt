package com.assignment.flickerfinder.utils

import android.app.AlertDialog
import android.content.Context

object Dialog {
    fun Context.showAlertDialog(
        title: String,
        message: String,
        ok: Pair<String, () -> Unit>,
        cancel: Pair<String, () -> Unit>? = null,
        cancellable: Boolean = true
    ) {

        val builder = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(cancellable)
            .setPositiveButton(ok.first) { _, _ -> ok.second() }

        cancel?.let {
            builder.setNegativeButton(it.first) { _, _ -> it.second() }
        }

        builder.create().show()
    }
}