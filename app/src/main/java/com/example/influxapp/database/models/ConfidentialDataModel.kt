package com.example.influxapp.database.models

data class ConfidentialDataModel(
    var dataID:String? = null,
    var cardNumber:String? = null,
    var cvvNumber:String? = null,
    var bankName:String? = null,
    var balance:String?=null
) {
}