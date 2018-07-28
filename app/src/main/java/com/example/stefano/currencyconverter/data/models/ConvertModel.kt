package com.example.stefano.currencyconverter.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.math.BigDecimal

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
) : Serializable {

    // Using BigDecimal values because of precision loss
    val buyingBigDecimal: BigDecimal
        get() = buyingRate.toBigDecimal()

    val sellingBigDecimal: BigDecimal
        get() = sellingRate.toBigDecimal()

    val unitBigDecimal: BigDecimal
        get() = unitValue.toBigDecimal()
}