package com.example.android.mybooks.data

import com.example.android.mybooks.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit

class RestClient {

    private val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder().apply {
        connectTimeout(60, TimeUnit.SECONDS)
        readTimeout(60, TimeUnit.SECONDS)
        writeTimeout(60, TimeUnit.SECONDS)
    }

    private val retrofit = Retrofit.Builder().apply {
        baseUrl(BuildConfig.BASE_URL)
        client(okHttpBuilder.build())
        addConverterFactory(SimpleXmlConverterFactory.create())
        addConverterFactory(GsonConverterFactory.create())
        addConverterFactory(ScalarsConverterFactory.create())
    }.build()

    var goodreadsService: GoodreadsService

    init {
        goodreadsService = retrofit.create(GoodreadsService::class.java)
    }
}