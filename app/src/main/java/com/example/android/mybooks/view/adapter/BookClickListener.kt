package com.example.android.mybooks.view.adapter

import com.example.android.mybooks.data.BookResponse
import com.example.android.mybooks.data.UserBooksResponse

interface BookClickListener {
    fun onBookClick(book: BookResponse?)
}