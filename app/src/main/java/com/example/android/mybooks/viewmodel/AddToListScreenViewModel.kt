package com.example.android.mybooks.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.mybooks.data.ShelvesListResponse
import com.example.android.mybooks.data.UserBooksResponse

class AddToListScreenViewModel : ViewModel() {
    var books: MutableLiveData<List<UserBooksResponse.Review>> = MutableLiveData()
    var shelves: MutableLiveData<List<ShelvesListResponse.Shelves.UserShelf>> = MutableLiveData()
}
