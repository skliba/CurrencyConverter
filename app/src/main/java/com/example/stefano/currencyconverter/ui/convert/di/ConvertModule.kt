package com.example.stefano.currencyconverter.ui.convert.di

import com.example.stefano.currencyconverter.ui.convert.ConvertActivity
import com.example.stefano.currencyconverter.data.interactors.ConvertInteractor
import com.example.stefano.currencyconverter.data.interactors.ConvertInteractorImpl
import com.example.stefano.currencyconverter.ui.convert.ConvertView
import dagger.Binds
import dagger.Module

@Module
abstract class ConvertModule {

    @Binds
    abstract fun bindView(activity: ConvertActivity): ConvertView

    @Binds
    abstract fun bindInteractor(interactor: ConvertInteractorImpl): ConvertInteractor
}
