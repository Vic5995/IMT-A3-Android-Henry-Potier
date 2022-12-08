package com.imt.andriamparivonylenglart

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://henri-potier.techx.fr")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}