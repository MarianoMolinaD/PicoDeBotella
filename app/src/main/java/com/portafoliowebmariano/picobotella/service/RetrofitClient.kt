package com.portafoliowebmariano.picobotella.service

import com.portafoliowebmariano.picobotella.utils.constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    fun getRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}