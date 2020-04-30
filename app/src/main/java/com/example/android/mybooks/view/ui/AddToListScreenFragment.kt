package com.example.android.mybooks.view.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.android.mybooks.R
import com.example.android.mybooks.databinding.AddToListScreenFragmentBinding
import com.example.android.mybooks.viewmodel.AddToListScreenViewModel
import com.example.android.mybooks.viewmodel.CurrentBooksViewModel

class AddToListScreenFragment : Fragment() {

    private lateinit var binding: AddToListScreenFragmentBinding

    companion object {
        fun newInstance() = AddToListScreenFragment()
    }

    private lateinit var viewModel: AddToListScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.add_to_list_screen_fragment,
            container,
            false
        )

        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.customActionBar)
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayShowHomeEnabled(true)

        viewModel = ViewModelProvider(requireActivity()).get(AddToListScreenViewModel::class.java)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
        binding.customActionBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

    }

}