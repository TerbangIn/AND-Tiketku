package com.ketarketir.tiketkuioflight.networking

import android.app.Application
import com.ketarketir.tiketkuioflight.datastoreprefs.UserManager
import dagger.Module
import dagger.hilt.InstallIn
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiClient {

    private const val BASE_URL =" https://be-tiketku-production.up.railway.app/"

    @Singleton
   @get:Provides
    val instance : ApiService by lazy {
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserManager(application: Application): UserManager {
        return UserManager.getInstance(application)
    }


}