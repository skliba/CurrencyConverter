package com.example.stefano.currencyconverter.ui.convert

import com.example.stefano.currencyconverter.data.interactors.ConvertInteractor
import com.example.stefano.currencyconverter.data.models.ConvertModel
import com.example.stefano.currencyconverter.ui.base.BasePresenter
import com.example.stefano.currencyconverter.util.types.CurrencyType
import com.example.stefano.currencyconverter.util.types.EmptyAmount
import org.threeten.bp.LocalDate
import javax.inject.Inject

class ConvertPresenter @Inject constructor(
        private val convertView: ConvertView,
        private val convertInteractor: ConvertInteractor
) : BasePresenter<ConvertView>(convertView) {

    private lateinit var conversionList: List<ConvertModel>

    override fun init() {
        fetchCurrencyInformation()
    }

    private fun fetchCurrencyInformation() {
        convertView.initUi()
        convertInteractor.execute(LocalDate.now().toString())
                .subscribe({
                               conversionList = it
                               view.updateCurrenciesList(extractCurrencies(it))
                           }, ::handleException)
    }

    private fun extractCurrencies(list: List<ConvertModel>) = list.map { it.currencyCode }


    override fun cancel() {

    }

    fun calculateCurrencyTransfer(fromCurrencySelection: CurrencyType,
                                  toCurrencySelection: CurrencyType,
                                  amount: String) {
        checkForErrors(amount)

    }

    private fun checkForErrors(amount: String) {
        when {
            amount.isEmpty() -> view.showError(EmptyAmount)
            amount == "0"    -> view.setResults(0, 0)
        }
    }
}