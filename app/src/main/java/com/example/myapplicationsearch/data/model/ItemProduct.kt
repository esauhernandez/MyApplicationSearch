package com.example.myapplicationsearch.data.model

data class ItemProduct(
    val domainCode: String,
    val keyword: String,
    val numberOfProducts: Int,
    val productDetails: List<ProductDetail>,
    val responseMessage: String,
    val responseStatus: String,
    val sortStrategy: String
)