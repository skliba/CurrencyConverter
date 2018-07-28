package com.example.stefano.currencyconverter.ui.convert

import com.example.stefano.currencyconverter.ui.base.BaseView
import com.example.stefano.currencyconverter.util.types.ConversionError

interface ConvertView : BaseView {

    fun updateCurrenciesList(currencyList: List<String>)
    fun showError(error: ConversionError)
    fun setConversionResult(sell: String)
}