package com.ketarketir.tiketkuioflight.model.destination

data class ListDataDestination(
    val departure: String,
    val arrival: String,
    val planeName: String,
    val date: String,
    val priceRange: String,
    val imageResId: Int
)
