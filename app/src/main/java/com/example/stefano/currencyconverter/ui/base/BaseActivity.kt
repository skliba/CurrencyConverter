package com.example.stefano.currencyconverter.ui.base

import android.os.Build
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.example.stefano.currencyconverter.R
import com.example.stefano.currencyconverter.util.extensions.getColorCompat
import com.example.stefano.currencyconverter.util.extensions.getDrawableCompat
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.toolbar.*

abstract class BaseActivity : AppCompatActivity(), BaseView {

    abstract fun providePresenter(): BasePresenter<BaseView>

    @get:LayoutRes protected abstract val layoutResourceId: Int

    private lateinit var presenter: BasePresenter<BaseView>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layoutResourceId)
        presenter = providePresenter()

        initToolbar()
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.navigationIcon = getDrawableCompat(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = getColorCompat(R.color.colorPrimaryDark)
        }
    }
}