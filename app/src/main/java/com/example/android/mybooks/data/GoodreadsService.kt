package com.example.android.mybooks.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GoodreadsService {
    /**
     * @return an xml represented by string it is suggested to parse it with https://developer.android.com/training/basics/network-ops/xml
     * or use http://simple.sourceforge.net/ converter
     */
    @GET("search.xml")
    fun search(@Query("key") key: String, @Query("q") query: String): Call<String>
}