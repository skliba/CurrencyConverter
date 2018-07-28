package com.example.stefano.currencyconverter.util.types

sealed class CurrencyType

object AUD : CurrencyType()
object CAD : CurrencyType()
object CZK : CurrencyType()
object DKK : CurrencyType()
object HUF : CurrencyType()
object JPY : CurrencyType()
object NOK : CurrencyType()
object SEK : CurrencyType()
object CHF : CurrencyType()
object GBP : CurrencyType()
object USD : CurrencyType()
object BAM : CurrencyType()
object EUR : CurrencyType()
object PLN : CurrencyType()
object Unknown : CurrencyType()

val CurrencyType.stringRepresentation: String
    get() = when (this) {
        AUD     -> "AUD"
        CAD     -> "CAD"
        CZK     -> "CZK"
        DKK     -> "DKK"
        HUF     -> "HUF"
        JPY     -> "JPY"
        NOK     -> "NOK"
        SEK     -> "SEK"
        CHF     -> "CHF"
        GBP     -> "GBP"
        USD     -> "USD"
        BAM     -> "BAM"
        EUR     -> "EUR"
        PLN     -> "PLN"
        Unknown -> "Unknown"
    }

val String.asCurrencyType: CurrencyType
    get() = when (this) {
        "AUD" -> AUD
        "CAD" -> CAD
        "CZK" -> CZK
        "DKK" -> DKK
        "HUF" -> HUF
        "JPY" -> JPY
        "NOK" -> NOK
        "SEK" -> SEK
        "CHF" -> CHF
        "GBP" -> GBP
        "USD" -> USD
        "BAM" -> BAM
        "EUR" -> EUR
        "PLN" -> PLN
        else  -> Unknown
    }
