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
    fun getUserBooks(
        @Query("key") key: String,
        @Query("id") userId: Int,
        @Query("search[query]") query: String = ""
    ): Call<UserBooksResponse>

    @GET("book/show.xml")
    fun getBookDetails(
        @Query("key") key: String,
        @Query("id") bookId: Int
    ): Call<BookDetailsResponse>

    @GET("shelf/list.xml")
    fun getUserShelves(
        @Query("key") key: String,
        @Query("user_id") userId: Int
    ): Call<ShelvesListResponse>

    @GET("shelf/add_to_shelf.xml")
    fun addToShelf(
        @Query("key") key: String,
        @Query("name") shelfName: String,
        @Query("book_id") bookId: Int,
        @Query("a") remove: String
    ): Call<ResponseBody>
}