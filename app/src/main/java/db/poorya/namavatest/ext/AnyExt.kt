package db.poorya.namavatest.ext

import android.annotation.SuppressLint
import android.util.Log
import db.poorya.namavatest.BuildConfig

@SuppressLint("LogNotTimber")
fun Any.logI(tag: String = "", throwable: Throwable? = null) {
    if (BuildConfig.DEBUG)
        Log.i(tag, "$this\n", throwable)
}

@SuppressLint("LogNotTimber")
fun Any.logE(tag: String = "", throwable: Throwable? = null) {
    if (BuildConfig.DEBUG)
        Log.e(tag, "$this\n", throwable)
}

@SuppressLint("LogNotTimber")
fun Any.logD(tag: String = "", throwable: Throwable? = null) {
    if (BuildConfig.DEBUG)
        Log.d(tag, "$this\n", throwable)
}

inline fun <reified NEW> Any.cast(): NEW? {
    return if (this.isCastable<NEW>())
        this as NEW
    else null
}

inline fun <reified NEW> Any.isCastable(): Boolean {
    return this is NEW
}