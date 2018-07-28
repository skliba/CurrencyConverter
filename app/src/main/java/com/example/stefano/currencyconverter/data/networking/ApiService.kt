package com.example.stefano.currencyconverter.data.networking

import com.example.stefano.currencyconverter.data.models.ConvertModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("rates/daily/")
    fun getRates(@Query("date") date: String): Single<List<ConvertModel>>
}