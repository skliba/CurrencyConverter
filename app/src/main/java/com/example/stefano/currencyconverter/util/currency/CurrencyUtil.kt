package com.example.stefano.currencyconverter.util.currency

import com.example.stefano.currencyconverter.data.models.ConvertModel
import java.math.BigDecimal

fun convertCurrencyToHrk(conversionList: List<ConvertModel>,
                         currency: String,
                         amount: BigDecimal) =
        conversionList
                .filter { it.currencyCode == currency }
                .map { it.sellingBigDecimal * (amount / it.unitBigDecimal) }
                .first()

fun convertFromHrk(conversionList: List<ConvertModel>,
                   currency: String,
                   amount: BigDecimal) =
        conversionList
                .filter { it.currencyCode == currency }
                .map { (amount / it.unitBigDecimal) / it.buyingBigDecimal }
                .first()