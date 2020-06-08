package com.tesonet.task

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.snack(text: String) {
    Snackbar.make(this, text, Snackbar.LENGTH_LONG).show()
}
