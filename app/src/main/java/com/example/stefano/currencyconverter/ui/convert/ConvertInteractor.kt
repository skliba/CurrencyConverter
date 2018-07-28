package com.example.stefano.currencyconverter.ui.convert

import com.example.stefano.currencyconverter.data.models.ConvertModel
import com.example.stefano.currencyconverter.data.networking.Interactor

interface ConvertInteractor : Interactor<String, ConvertModel>

class ConvertInteractorImpl : ConvertInteractor {

    override fun execute(inputModel: String): ConvertModel = ConvertModel()

}