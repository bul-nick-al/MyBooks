package com.example.android.mybooks.data

interface BookResponse {
    fun getBookId(): Int?
    fun getBookTitle(): String?
    fun getBookImageUrl(): String?
}