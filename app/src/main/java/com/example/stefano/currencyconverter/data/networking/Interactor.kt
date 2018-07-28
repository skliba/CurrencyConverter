package com.example.stefano.currencyconverter.data.networking

interface Interactor<Input, Output> {

    fun execute(inputModel: Input): Output
}