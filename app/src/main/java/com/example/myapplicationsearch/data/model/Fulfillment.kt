package com.example.myapplicationsearch.data.model

data class Fulfillment(
    val isPUT: Boolean,
    val isS2H: Boolean,
    val isS2S: Boolean,
    val isSOI: Boolean,
    val s2HDisplayFlags: List<String>,
    val s2SDisplayFlags: List<String>
)