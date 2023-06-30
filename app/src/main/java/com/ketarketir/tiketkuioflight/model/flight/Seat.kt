package com.ketarketir.tiketkuioflight.model.flight


import com.google.gson.annotations.SerializedName

data class Seat(
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