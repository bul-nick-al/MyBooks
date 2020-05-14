package com.example.android.mybooks.di

import com.example.android.mybooks.BuildConfig
import com.example.android.mybooks.data.GoodreadsAuth
import com.example.android.mybooks.data.RestClient
import com.github.scribejava.core.builder.ServiceBuilder
import org.koin.dsl.module

val appModule = module {
    single { (token: String?, secret: String?) -> RestClient(token, secret) }

    single { ServiceBuilder(BuildConfig.GOODREADS_API_KEY)
        .apiSecret(BuildConfig.GOODREADS_API_SECRET)
        .build(GoodreadsAuth()) }
}