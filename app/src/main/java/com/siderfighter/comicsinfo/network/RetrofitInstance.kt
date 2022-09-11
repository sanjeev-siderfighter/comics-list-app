package com.siderfighter.comicsinfo.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val RAJ_COMICS_INFO_BASE_URL = "https://script.google.com/macros/s/" // todo: move to a secure location

object RetrofitInstance {
    fun getInstance(): Retrofit {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(RAJ_COMICS_INFO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        return retrofitBuilder.build()
    }
}