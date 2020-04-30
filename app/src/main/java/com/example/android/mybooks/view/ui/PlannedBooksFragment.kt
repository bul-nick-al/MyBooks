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
import com.example.android.mybooks.databinding.PlannedBooksFragmentBinding
import com.example.android.mybooks.viewmodel.PlannedBooksViewModel

class PlannedBooksFragment : Fragment() {

    private lateinit var binding: PlannedBooksFragmentBinding

    companion object {
        fun newInstance() = PlannedBooksFragment()
    }

    private lateinit var viewModel: PlannedBooksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.planned_books_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(requireActivity()).get(PlannedBooksViewModel::class.java)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}
