package com.example.stefano.currencyconverter.ui.convert

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.stefano.currencyconverter.R
import com.example.stefano.currencyconverter.ui.base.BaseActivity
import com.example.stefano.currencyconverter.ui.base.BasePresenter
import com.example.stefano.currencyconverter.ui.base.BaseView
import com.example.stefano.currencyconverter.util.extensions.hide
import com.example.stefano.currencyconverter.util.extensions.visible
import com.example.stefano.currencyconverter.util.types.AUD
import com.example.stefano.currencyconverter.util.types.ConversionError
import com.example.stefano.currencyconverter.util.types.CurrencyType
import com.example.stefano.currencyconverter.util.types.asCurrencyType
import com.example.stefano.currencyconverter.util.types.stringRepresentation
import kotlinx.android.synthetic.main.activity_convert.*
import kotlinx.android.synthetic.main.no_network_layout.*
import javax.inject.Inject

class ConvertActivity : BaseActivity(), ConvertView {

    @Inject lateinit var presenter: ConvertPresenter

    override val layoutResourceId: Int = R.layout.activity_convert
    override fun providePresenter(): BasePresenter<BaseView> = presenter

    private var fromCurrencySelection: CurrencyType = AUD
    private var toCurrencySelection: CurrencyType = AUD

    private val fromCurrencySelected = object : AdapterView.OnItemSelectedListener {

        override fun onNothingSelected(parent: AdapterView<*>?) {
            //nothing here
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            fromCurrencySelection = fromCurrency.selectedItem.toString().asCurrencyType

            if (amountValue.text.isNotEmpty()) {
                presenter.calculateCurrencyTransfer(
                        fromCurrencySelection,
                        toCurrencySelection,
                        amountValue.text.toString())
            }
        }
    }

    private val toCurrencySelected = object : AdapterView.OnItemSelectedListener {

        override fun onNothingSelected(parent: AdapterView<*>?) {
            //nothing here
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            toCurrencySelection = toCurrency.selectedItem.toString().asCurrencyType

            if (amountValue.text.isNotEmpty()) {
                presenter.calculateCurrencyTransfer(
                        fromCurrencySelection,
                        toCurrencySelection,
                        amountValue.text.toString())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.init()
    }

    override fun initUi() {
        calculate.setOnClickListener {
            presenter.calculateCurrencyTransfer(fromCurrencySelection,
                                                toCurrencySelection,
                                                amountValue.text.toString()
            )
        }
    }

    override fun updateCurrenciesList(currencyList: List<String>) {
        showContentLayout()
        val currenciesAdapter = ArrayAdapter<String>(this, R.layout.spinner_item, currencyList)

        fromCurrency.apply {
            adapter = currenciesAdapter
            // Here just not to trigger selectionListener on init
            setSelection(0, false)
            onItemSelectedListener = fromCurrencySelected
        }

        toCurrency.apply {
            adapter = currenciesAdapter
            // Here just not to trigger selectionListener on init
            setSelection(0, false)
            onItemSelectedListener = toCurrencySelected
        }
    }

    override fun showError(error: ConversionError) {
        amount.error = error.stringRepresentation
        resultValue.hide()
    }

    override fun setConversionResult(sell: String) {
        amount.error = null

        resultValue.apply {
            visible()
            text = getString(R.string.result, sell)
        }
    }

    override fun showNoConnectionLayout() {
        noNetworkLayout.visible()
        content.hide()
        noNetworkLayout.setOnClickListener {
            presenter.init()
        }
    }

    override fun showContentLayout() {
        content.visible()
        noNetworkLayout.hide()
    }
}
