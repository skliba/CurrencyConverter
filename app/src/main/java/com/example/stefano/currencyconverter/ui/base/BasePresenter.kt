package com.example.stefano.currencyconverter.ui.base

import java.net.UnknownHostException

abstract class BasePresenter<out T : BaseView>(protected val view: T) {

    abstract fun init()
    abstract fun cancel()

    protected open fun handleException(throwable: Throwable) {
        when (throwable) {
            is UnknownHostException -> {
                view.showNoConnectionLayout()
            }
        }
    }
}