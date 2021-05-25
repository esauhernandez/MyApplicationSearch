package com.example.myapplicationsearch.data.model

data class VariantData(
    val isAvailable: String,
    val ownedInventory: String,
    val productId: String,
    val productImageUrl: String,
    val productPageUrl: String,
    val productSrcSet: String,
    val skuCoverage: String,
    val swatchImageUrl: String,
    val swatchSrcSet: String,
    val title: String,
    val usItemId: String,
    val variantValues: List<VariantValue>
)