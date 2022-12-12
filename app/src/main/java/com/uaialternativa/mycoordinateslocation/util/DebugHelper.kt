package com.uaialternativa.mycoordinateslocation.util

import android.util.Log

val showMessage: Boolean = true
fun dprint(message: String) {
    if (showMessage) {
        Log.i("___DEV_INFO___", message)
    }
}

