package com.example.android.mybooks.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.mybooks.data.UserBooksResponse

class PlannedBooksViewModel : ViewModel(), MyBooksViewModelInterface {

    var books: MutableLiveData<List<UserBooksResponse.Review>> = MutableLiveData()

    override fun getBooksList(): MutableLiveData<List<UserBooksResponse.Review>> {
        return books
    }
}
