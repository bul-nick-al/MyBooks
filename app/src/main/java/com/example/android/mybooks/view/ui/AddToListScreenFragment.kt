package com.example.android.mybooks.view.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.android.mybooks.R
import com.example.android.mybooks.viewmodel.AddToListScreenViewModel

class AddToListScreenFragment : Fragment() {

    companion object {
        fun newInstance() = AddToListScreenFragment()
    }

    private lateinit var viewModel: AddToListScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_to_list_screen_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddToListScreenViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
