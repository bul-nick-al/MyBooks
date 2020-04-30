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
import com.example.android.mybooks.databinding.SettingsScreenFragmentBinding
import com.example.android.mybooks.viewmodel.CurrentBooksViewModel
import com.example.android.mybooks.viewmodel.SettingsScreenViewModel

class SettingsScreenFragment : Fragment() {

    private lateinit var binding: SettingsScreenFragmentBinding

    companion object {
        fun newInstance() =
            SettingsScreenFragment()
    }

    private lateinit var viewModel: SettingsScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.settings_screen_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(requireActivity()).get(SettingsScreenViewModel::class.java)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}
