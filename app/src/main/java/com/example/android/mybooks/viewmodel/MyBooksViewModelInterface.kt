package com.example.android.mybooks.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.android.mybooks.data.UserBooksResponse

interface MyBooksViewModelInterface {
    fun getBooksList(): MutableLiveData<List<UserBooksResponse.Review>>;
}