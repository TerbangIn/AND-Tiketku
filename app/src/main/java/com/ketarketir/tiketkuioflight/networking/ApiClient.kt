package com.ketarketir.tiketkuioflight.networking

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    object RetrofitClient {
        private const val BASE_URL =" https://be-tiketku-production.up.railway.app/"
//        private const val API_KEY = "6cb32867a94de7a19988927b1aece140"

//        private val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(ApiKeyInterceptor(API_KEY))
//            .build()


        val instance : ApiService by lazy {
            val retrofit= Retrofit.Builder()
                .baseUrl(BASE_URL)
//                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(ApiService::class.java)
        }
    }
}