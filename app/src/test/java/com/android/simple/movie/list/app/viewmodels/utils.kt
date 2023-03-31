package com.android.simple.movie.list.app.viewmodels

import io.reactivex.rxjava3.observers.TestObserver

/** It only checks the last value of given observer. */
fun <T : Any> TestObserver<T>.assertValueLast(predicate: (T) -> Boolean) {
    val size = this.values().size
    this.assertValueAt(size - 1) { predicate(it) }
}