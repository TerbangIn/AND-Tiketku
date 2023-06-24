package com.ketarketir.tiketkuioflight.model.user


import com.google.gson.annotations.SerializedName

data class SeatX(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("flight_id")
    val flightId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("seat_number")
    val seatNumber: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("ticket_id")
    val ticketId: Any,
    @SerializedName("updatedAt")
    val updatedAt: String
)