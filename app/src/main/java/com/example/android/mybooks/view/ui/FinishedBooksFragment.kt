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
import com.example.android.mybooks.databinding.FinishedBooksFragmentBinding
import com.example.android.mybooks.viewmodel.CurrentBooksViewModel
import com.example.android.mybooks.viewmodel.FinishedBooksViewModel

class FinishedBooksFragment : Fragment() {

    private lateinit var binding: FinishedBooksFragmentBinding

    companion object {
        fun newInstance() = FinishedBooksFragment()
    }

    private lateinit var viewModel: FinishedBooksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.finished_books_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(requireActivity()).get(FinishedBooksViewModel::class.java)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}
