package com.siderfighter.comicsinfo.network

import com.siderfighter.comicsinfo.data.repository.rajcomics.RajComicsRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val RAJ_COMICS_INFO_BASE_URL = "https://script.google.com/macros/s/" // todo: move to a secure location

object RetrofitInstance {
    fun getRajComicsInstance(): RajComicsRepository {
        val retrofit = Retrofit.Builder()
            .baseUrl(RAJ_COMICS_INFO_BASE_URL)
            .client(getLoggingInterceptor().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(RajComicsRepository::class.java)
    }

    private fun getLoggingInterceptor(): OkHttpClient.Builder {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor)
    }
}