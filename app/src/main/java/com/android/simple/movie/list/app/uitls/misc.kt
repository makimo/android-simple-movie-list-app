package com.android.simple.movie.list.app.uitls

import android.app.Activity
import android.view.View
import com.android.simple.movie.list.app.App
import java.io.IOException

fun Activity.hideSystemNavigationBar() {
    val flags = window.decorView.systemUiVisibility or (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            )
    window.decorView.systemUiVisibility = flags
}

fun getFileFromAssets(fileName: String) =
    try {
        val inputStream = App.instance.baseContext.assets.open(fileName)
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        String(buffer)
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }