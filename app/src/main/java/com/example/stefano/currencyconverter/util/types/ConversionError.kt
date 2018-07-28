package com.example.stefano.currencyconverter.util.types

sealed class ConversionError

object EmptyAmount : ConversionError()

val ConversionError.stringRepresentation: String
    get() = when (this) {
        is EmptyAmount -> "You need to input an amount you wish to exchange"
    }
