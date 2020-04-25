package com.example.android.mybooks.service.repository

import com.example.android.mybooks.service.model.Book

object MockData {
    val books: List<Book> = List(10) { i -> Book("book $i") }
}