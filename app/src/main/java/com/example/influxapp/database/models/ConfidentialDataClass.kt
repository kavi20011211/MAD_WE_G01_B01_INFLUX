package com.example.influxapp.database.models

class ConfidentialDataClass(
    private val cardNumber:String?=null,
    private val cvvNumber:String?=null,
    private val bankName:String?=null
) {

    fun getCardNumber() = cardNumber

    fun getCvvNumber() = cvvNumber

    fun getBankNumber() = bankName
}