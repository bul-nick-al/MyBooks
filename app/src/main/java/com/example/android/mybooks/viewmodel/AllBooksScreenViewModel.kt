package com.example.android.mybooks.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.mybooks.data.SearchBooksResponse
import com.example.android.mybooks.service.model.Book

class AllBooksScreenViewModel : ViewModel() {
    var books: MutableLiveData<List<SearchBooksResponse.Search.Work.BestBook>> = MutableLiveData()
}
