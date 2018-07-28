package com.example.stefano.currencyconverter.util.extensions

import com.example.stefano.currencyconverter.data.exceptions.ApiException
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.handleErrors(): Single<T> = compose<T> { request ->
    request.doOnError { e ->
        val message = e.message
        Single.error<T>(ApiException(message))
    }
}

fun <T> Single<T>.applySchedulers(): Single<T> = compose {
    it
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
