package com.example.stefano.currencyconverter.ui.base

abstract class BasePresenter<out T : BaseView>(protected val view: T) {

    abstract fun cancel()
}