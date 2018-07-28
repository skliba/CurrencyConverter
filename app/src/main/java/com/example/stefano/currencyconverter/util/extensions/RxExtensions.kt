package com.example.stefano.currencyconverter.util.extensions

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.handleErrors() {
    doOnError {

    }
}

fun <T> Single<T>.applySchedulers(): Single<T> = compose {
    it
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
