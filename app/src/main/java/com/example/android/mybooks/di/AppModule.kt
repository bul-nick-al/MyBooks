package com.example.android.mybooks.di

import com.example.android.mybooks.data.RestClient
import org.koin.dsl.module

val appModule = module {
    single { RestClient() }
}