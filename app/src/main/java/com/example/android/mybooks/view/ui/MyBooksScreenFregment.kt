package com.example.android.mybooks.view.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.android.mybooks.R
import com.example.android.mybooks.viewmodel.MyBooksScreenFregmentViewModel

class MyBooksScreenFregment : Fragment() {

    companion object {
        fun newInstance() = MyBooksScreenFregment()
    }

    private lateinit var viewModel: MyBooksScreenFregmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my_books_screen_fregment_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MyBooksScreenFregmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
