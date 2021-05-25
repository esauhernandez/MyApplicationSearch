package com.example.myapplicationsearch.data.model

data class PrimaryOffer(
    val currencyCode: String,
    val listPrice: Int,
    val maxPrice: Double,
    val minPrice: Int,
    val offerId: String,
    val offerPrice: Int,
    val savingsAmount: Int,
    val showMinMaxPrice: Boolean,
    val showWasPrice: Boolean,
    val unitPriceDisplayCondition: String
)