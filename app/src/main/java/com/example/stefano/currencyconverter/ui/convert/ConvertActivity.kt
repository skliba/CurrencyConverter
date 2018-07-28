package com.example.stefano.currencyconverter.ui.convert

import android.os.Bundle
import com.example.stefano.currencyconverter.R
import com.example.stefano.currencyconverter.ui.base.BaseActivity
import com.example.stefano.currencyconverter.ui.base.BasePresenter
import com.example.stefano.currencyconverter.ui.base.BaseView
import javax.inject.Inject

class ConvertActivity : BaseActivity(), ConvertView {

    @Inject lateinit var presenter: ConvertPresenter

    override val layoutResourceId: Int = R.layout.activity_convert
    override fun providePresenter(): BasePresenter<BaseView> = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initUi() {

    }
}
