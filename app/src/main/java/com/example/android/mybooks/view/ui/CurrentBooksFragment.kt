package com.example.android.mybooks.view.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.android.mybooks.R
import com.example.android.mybooks.databinding.CurrentBooksFragmentBinding
import com.example.android.mybooks.viewmodel.CurrentBooksViewModel

class CurrentBooksFragment : Fragment() {

    private lateinit var binding: CurrentBooksFragmentBinding

    companion object {
        fun newInstance() = CurrentBooksFragment()
    }

    private lateinit var viewModel: CurrentBooksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.current_books_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(requireActivity()).get(CurrentBooksViewModel::class.java)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.openButton.setOnClickListener {
            findNavController().navigate(R.id.action_global_bookOverviewFragment)
        }
        // TODO: Use the ViewModel
    }

}
