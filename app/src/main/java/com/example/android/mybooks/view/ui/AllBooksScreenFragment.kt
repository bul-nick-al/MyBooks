package com.example.android.mybooks.view.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

import com.example.android.mybooks.R
import com.example.android.mybooks.databinding.AllBooksScreenFragmentBinding
import com.example.android.mybooks.viewmodel.AllBooksScreenViewModel
import com.example.android.mybooks.viewmodel.CurrentBooksViewModel
import kotlinx.android.synthetic.main.all_books_screen_fragment.*

class AllBooksScreenFragment : Fragment() {

    private lateinit var binding: AllBooksScreenFragmentBinding

    companion object {
        fun newInstance() = AllBooksScreenFragment()
    }

    private lateinit var viewModel: AllBooksScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.all_books_screen_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(requireActivity()).get(AllBooksScreenViewModel::class.java)
        binding.booksSearchBar.onSearch = {}

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}
