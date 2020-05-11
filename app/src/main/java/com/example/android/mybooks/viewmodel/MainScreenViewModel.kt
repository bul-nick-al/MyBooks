package com.example.android.mybooks.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.mybooks.data.UserBooksResponse
import com.example.android.mybooks.service.model.Book
import com.example.android.mybooks.service.repository.MockData

class MainScreenViewModel : ViewModel() {
    var books: MutableLiveData<List<UserBooksResponse.Review>> = MutableLiveData()
}
