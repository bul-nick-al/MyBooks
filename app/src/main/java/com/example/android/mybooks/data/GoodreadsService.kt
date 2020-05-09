package com.example.android.mybooks.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GoodreadsService {

    @GET("search.xml?search=title")
    fun searchBooks(@Query("key") key: String, @Query("q") query: String): Call<SearchBooksResponse>
}