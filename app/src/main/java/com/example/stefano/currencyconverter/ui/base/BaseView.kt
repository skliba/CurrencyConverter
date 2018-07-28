package com.example.stefano.currencyconverter.ui.base

interface BaseView {
    fun showProgress()
    fun hideProgress()
    fun initUi()
    fun showError(message: String)
    fun showNoConnectionLayout()
    fun showContentLayout()
}