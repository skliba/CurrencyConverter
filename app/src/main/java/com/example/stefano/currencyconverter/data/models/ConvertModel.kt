package com.example.stefano.currencyconverter.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ConvertModel(
        @SerializedName("currency_code")
        val currencyCode: String = "",

        @SerializedName("unit_value")
        val unitValue: Int = 0,

        @SerializedName("buying_rate")
        val buyingRate: String = "",

        @SerializedName("median_rate")
        val medianRate: String = "",

        @SerializedName("selling_rate")
        val sellingRate: String = ""
) : Serializable