package com.ketarketir.tiketkuioflight.model.user


import com.google.gson.annotations.SerializedName

data class Transaction(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("kode_booking")
    val kodeBooking: String,
    @SerializedName("midtrans_booking_code")
    val midtransBookingCode: String,
    @SerializedName("midtrans_url")
    val midtransUrl: String,
    @SerializedName("payment")
    val payment: Any,
    @SerializedName("payment_id")
    val paymentId: Any,
    @SerializedName("payment_status")
    val paymentStatus: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("tiket")
    val tiket: List<Tiket>,
    @SerializedName("total_price")
    val totalPrice: Int,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: Int
)