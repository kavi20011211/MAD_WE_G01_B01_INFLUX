package com.example.influxapp.model


data class Income(
    val category: String = "",
    val incomeType: String = "",
    val amount: Number? = null
)
