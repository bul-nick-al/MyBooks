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
import com.example.android.mybooks.databinding.AuthFragmentBinding
import com.example.android.mybooks.viewmodel.AuthViewModel
import com.example.android.mybooks.viewmodel.CurrentBooksViewModel

class AuthFragment : Fragment() {

    private lateinit var binding: AuthFragmentBinding

    companion object {
        fun newInstance() = AuthFragment()
    }

    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):  View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.auth_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}
