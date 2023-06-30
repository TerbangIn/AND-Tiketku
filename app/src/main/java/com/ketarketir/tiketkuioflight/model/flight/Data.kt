package com.ketarketir.tiketkuioflight.model.flight


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("adult_price_percentage")
    val adultPricePercentage: Int,
    @SerializedName("airline")
    val airline: String,
    @SerializedName("arrival_date")
    val arrivalDate: String,
    @SerializedName("baby_price_percentage")
    val babyPricePercentage: Int,
    @SerializedName("business_class_price")
    val businessClassPrice: Any,
    @SerializedName("capacity")
    val capacity: Int,
    @SerializedName("child_price_percentage")
    val childPricePercentage: Int,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("departure_date")
    val departureDate: String,
    @SerializedName("destination")
    val destination: Destination,
    @SerializedName("destination_airport")
    val destinationAirport: Int,
    @SerializedName("economy_class_price")
    val economyClassPrice: Int,
    @SerializedName("first_class_price")
    val firstClassPrice: Int,
    @SerializedName("flight_number")
    val flightNumber: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("information")
    val information: List<Information>,
    @SerializedName("premium_price")
    val premiumPrice: Any,
    @SerializedName("seat")
    val seat: List<Seat>,
    @SerializedName("source")
    val source: Source,
    @SerializedName("source_airport")
    val sourceAirport: Int,
    @SerializedName("updatedAt")
    val updatedAt: String
)