package com.example.stefano.currencyconverter.ui.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection

abstract class BaseActivity : AppCompatActivity(), BaseView {

    @get:LayoutRes protected abstract val layoutResourceId: Int
    private lateinit var presenter: BasePresenter<BaseView>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layoutResourceId)
        presenter = providePresenter()
    }

    abstract fun providePresenter(): BasePresenter<BaseView>

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

}