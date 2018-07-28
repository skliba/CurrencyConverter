package com.example.stefano.currencyconverter.ui.convert

import dagger.Binds
import dagger.Module

@Module
abstract class ConvertModule {

    @Binds
    abstract fun bindView(activity: ConvertActivity): ConvertView
}
