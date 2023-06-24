package com.ketarketir.tiketkuioflight.model.user


import com.google.gson.annotations.SerializedName

data class DataResponseUserNotification(
    @SerializedName("data")
    val `data`: List<DataXXX>,
    @SerializedName("status")
    val status: String
)