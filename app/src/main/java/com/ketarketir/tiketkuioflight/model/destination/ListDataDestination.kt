package com.ketarketir.tiketkuioflight.model.destination

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListDataDestination(
    val id: Int,
    val departure: String,
    val arrival: String,
    val planeName: String,
    val date: String,
    val priceRange: String,
    val imageResId: Int,
    val overview: String
) : Parcelable

