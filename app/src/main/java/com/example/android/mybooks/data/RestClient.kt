package com.example.android.mybooks.data

import com.example.android.mybooks.BuildConfig
import com.github.scribejava.core.model.OAuthConstants.CONSUMER_KEY
import com.github.scribejava.core.model.OAuthConstants.CONSUMER_SECRET
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer
import se.akerfeldt.okhttp.signpost.SigningInterceptor
import java.util.concurrent.TimeUnit


class RestClient(token: String?, secret: String?) {

    private val consumer = token?.let { token ->
        secret?.let { secret ->
            OkHttpOAuthConsumer("cj9PSZ5nNyqmYS48SM2Q", "QR5Rnf7nDeJaE97RHBGnq7yjes1tNcKd7yn4I9A8AKA").apply {
                setTokenWithSecret(token, secret)
            }
        }
    }

    private val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder().apply {
        consumer?.let {
            addInterceptor(SigningInterceptor(it))
        }
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