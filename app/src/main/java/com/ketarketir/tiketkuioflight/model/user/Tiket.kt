package com.ketarketir.tiketkuioflight.model.user


import com.google.gson.annotations.SerializedName

data class Tiket(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("flight")
    val flight: Flight,
    @SerializedName("flight_id")
    val flightId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("passenger")
    val passenger: Passenger,
    @SerializedName("passenger_id")
    val passengerId: Int,
    @SerializedName("price")
    val price: Long,
    @SerializedName("seat")
    val seat: SeatX,
    @SerializedName("seat_id")
    val seatId: Int,
    @SerializedName("transaction_id")
    val transactionId: Int,
    @SerializedName("type_of_class")
    val typeOfClass: String,
    @SerializedName("type_of_passenger")
    val typeOfPassenger: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)