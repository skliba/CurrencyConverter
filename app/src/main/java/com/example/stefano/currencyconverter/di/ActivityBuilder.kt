package com.example.stefano.currencyconverter.di

import com.example.stefano.currencyconverter.ui.convert.ConvertActivity
import com.example.stefano.currencyconverter.ui.convert.ConvertModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(ConvertModule::class))
    abstract fun bindConvertActivity(): ConvertActivity
}