package com.example.android.mybooks.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.mybooks.data.BookDetailsResponse
import com.example.android.mybooks.data.UserBooksResponse

class BookOverviewViewModel : ViewModel() {
    var book: MutableLiveData<BookDetailsResponse.Book> = MutableLiveData()
}
