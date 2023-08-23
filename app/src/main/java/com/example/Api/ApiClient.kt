package com.example.Api

import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val okHttp = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor()
            .apply { level  = HttpLoggingInterceptor.Level.BODY })
        .build()

    var retrofit = Retrofit.Builder()
        .baseUrl("http://13.37.106.218/")
        .client(okHttp)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun <T> buildApiClient(apiInterface: Class<T>): T {
        return retrofit.create(apiInterface);
    }

}