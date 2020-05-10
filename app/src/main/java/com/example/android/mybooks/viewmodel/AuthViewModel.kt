package com.example.android.mybooks.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.scribejava.core.model.OAuth1RequestToken

class AuthViewModel : ViewModel() {
    val requestToken = MutableLiveData<OAuth1RequestToken>()

    fun setToken(token: OAuth1RequestToken) {
        requestToken.value = token
    }
}
