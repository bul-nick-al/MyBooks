package com.example.android.mybooks.view.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs

import com.example.android.mybooks.R
import com.example.android.mybooks.databinding.BookOverviewFragmentBinding
import com.example.android.mybooks.viewmodel.BookOverviewViewModel
import com.example.android.mybooks.viewmodel.MainScreenViewModel

class BookOverviewFragment : Fragment() {

    private lateinit var binding: BookOverviewFragmentBinding
    val args: BookOverviewFragmentArgs by navArgs()

    companion object {
        fun newInstance() = BookOverviewFragment()
    }

    private lateinit var viewModel: BookOverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.book_overview_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(requireActivity()).get(BookOverviewViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bookTitle.text = args.bookTitle
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(BookOverviewViewModel::class.java)
    }

}
