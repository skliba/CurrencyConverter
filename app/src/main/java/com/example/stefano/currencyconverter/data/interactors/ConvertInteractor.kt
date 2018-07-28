package com.example.stefano.currencyconverter.data.interactors

import com.example.stefano.currencyconverter.data.models.ConvertModel
import com.example.stefano.currencyconverter.data.networking.ApiService
import com.example.stefano.currencyconverter.data.networking.Interactor
import com.example.stefano.currencyconverter.util.extensions.applySchedulers
import com.example.stefano.currencyconverter.util.extensions.handleErrors
import io.reactivex.Single
import javax.inject.Inject

interface ConvertInteractor : Interactor<String, Single<List<ConvertModel>>>

class ConvertInteractorImpl @Inject constructor(
        private val apiService: ApiService
) : ConvertInteractor {

    override fun execute(inputModel: String): Single<List<ConvertModel>> =
            apiService
                    .getRates(inputModel)
                    .handleErrors()
                    .applySchedulers()
}