package com.example.android.mybooks.data

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GoodreadsService {

    @GET("search.xml?search=title")
    fun searchBooks(@Query("key") key: String, @Query("q") query: String): Call<SearchBooksResponse>

    @GET("api/auth_user.xml")
    fun getUserId(): Call<UserIdResponse>

    @GET("review/list.xml?v=2")
    fun getUserBooks(@Query("key") key: String, @Query("id") userId: Int): Call<UserBooksResponse>

    @GET("book/show.xml")
    fun getBookDetails(
        @Query("key") key: String,
        @Query("id") bookId: Int
    ): Call<BookDetailsResponse>
}