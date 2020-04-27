package com.example.android.mybooks.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.mybooks.R
import com.example.android.mybooks.data.RestClient

class MainActivity : AppCompatActivity() {

    lateinit var restClient: RestClient

    override fun onCreate(savedInstanceState: Bundle?) {
        restClient = RestClient()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
