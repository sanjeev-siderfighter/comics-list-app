package com.siderfighter.comicsinfo.di

import com.siderfighter.comicsinfo.data.endpoints.RajComicsNetworkInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val RAJ_COMICS_INFO_BASE_URL = "https://script.google.com/macros/s/" // todo: move to a secure location

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun getRajComicsInstance(): RajComicsNetworkInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(RAJ_COMICS_INFO_BASE_URL)
            .client(getLoggingInterceptor().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(RajComicsNetworkInterface::class.java)
    }

    private fun getLoggingInterceptor(): OkHttpClient.Builder {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor)
    }
}