package com.example.stefano.currencyconverter.ui.convert

import com.example.stefano.currencyconverter.data.interactors.ConvertInteractor
import com.example.stefano.currencyconverter.data.models.ConvertModel
import com.example.stefano.currencyconverter.ui.base.BasePresenter
import com.example.stefano.currencyconverter.util.currency.convertCurrencyToHrk
import com.example.stefano.currencyconverter.util.currency.convertFromHrk
import com.example.stefano.currencyconverter.util.types.CurrencyType
import com.example.stefano.currencyconverter.util.types.EmptyAmount
import com.example.stefano.currencyconverter.util.types.HRK
import com.example.stefano.currencyconverter.util.types.stringRepresentation
import org.threeten.bp.LocalDate
import javax.inject.Inject

private const val NO_RESULT = "0.0"

class ConvertPresenter @Inject constructor(
        private val convertView: ConvertView,
        private val convertInteractor: ConvertInteractor
) : BasePresenter<ConvertView>(convertView) {

    private lateinit var conversionList: List<ConvertModel>

    override fun init() {
        fetchCurrencyInformation()
    }

    fun calculateCurrencyTransfer(fromCurrency: CurrencyType,
                                  toCurrency: CurrencyType,
                                  amount: String) {

        if (!checkForErrors(amount)) {
            if (!checkSameCurrencies(fromCurrency, toCurrency)) {
                convertCurrencies(fromCurrency, toCurrency, amount.toDouble())
            } else {
                view.setConversionResult(amount.toDouble().toString())
            }
        }
    }

    private fun fetchCurrencyInformation() {
        convertView.initUi()
        convertInteractor.execute(LocalDate.now().toString())
                .subscribe({
                               conversionList = it
                               view.updateCurrenciesList(extractCurrencies(it))
                           }, ::handleException)
    }

    private fun convertCurrencies(fromCurrency: CurrencyType,
                                  toCurrency: CurrencyType,
                                  amount: Double) {
        val result = when {

            fromCurrency == HRK -> convertFromHrk(conversionList,
                                                  toCurrency.stringRepresentation,
                                                  amount.toBigDecimal())

            toCurrency == HRK   -> convertCurrencyToHrk(conversionList,
                                                        fromCurrency.stringRepresentation,
                                                        amount.toBigDecimal())
            else                -> {

                val firstCurrencyInHrk = convertCurrencyToHrk(
                        conversionList,
                        currency = fromCurrency.stringRepresentation,
                        amount = amount.toBigDecimal())

                convertFromHrk(
                        conversionList,
                        currency = toCurrency.stringRepresentation,
                        amount = firstCurrencyInHrk)
            }
        }

        view.setConversionResult(result.toString())
    }

    private fun extractCurrencies(list: List<ConvertModel>): MutableList<String> {
        val modifiedList = list.map { it.currencyCode }.toMutableList()
        modifiedList.add(HRK.stringRepresentation)
        return modifiedList
    }

    private fun checkSameCurrencies(fromCurrency: CurrencyType, toCurrency: CurrencyType): Boolean =
            fromCurrency.stringRepresentation == toCurrency.stringRepresentation

    private fun checkForErrors(amount: String): Boolean {
        when {
            amount.isEmpty() -> {
                view.showError(EmptyAmount)
                return true
            }
            amount == "0"    -> {
                view.setConversionResult(NO_RESULT)
                return true
            }
        }
        return false
    }

    override fun cancel() {

    }
}