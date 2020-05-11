package com.example.android.mybooks.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    val userId: MutableLiveData<Int> = MutableLiveData()
    val accessToken: MutableLiveData<String> = MutableLiveData()
    val accessTokenSecret: MutableLiveData<String> = MutableLiveData()
}