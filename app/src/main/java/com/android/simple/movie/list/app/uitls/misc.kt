package com.android.simple.movie.list.app.uitls

import android.app.Activity
import android.view.View

fun Activity.hideSystemNavigationBar() {
    val flags = window.decorView.systemUiVisibility or (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            )
    window.decorView.systemUiVisibility = flags
}