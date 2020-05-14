package com.example.android.mybooks.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.android.mybooks.R
import com.example.android.mybooks.databinding.CurrentBooksFragmentBinding
import com.example.android.mybooks.databinding.FinishedBooksFragmentBinding
import com.example.android.mybooks.view.adapter.MyBooksRecyclerAdapter
import com.example.android.mybooks.viewmodel.CurrentBooksViewModel
import com.example.android.mybooks.viewmodel.MyBooksViewModelInterface

class CurrentBooksFragment : LoadShelfDataFragment() {
    private lateinit var binding: CurrentBooksFragmentBinding
    val screenViewModel: CurrentBooksViewModel by activityViewModels()

    companion object {
        fun newInstance() = FinishedBooksFragment()
    }

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

        binding.currentBooksRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        binding.currentBooksRecyclerView.adapter = getAdapter()

        screenViewModel.books.observe(viewLifecycleOwner, Observer { books -> setBooks(books) })

        loadBooks()

        return binding.root
    }

    override fun getShelfName(): String {
        return "currently-reading"
    }

    override fun getScreenViewModel(): MyBooksViewModelInterface {
        return screenViewModel
    }

    override fun getBindingAdapter(): MyBooksRecyclerAdapter {
        return binding.currentBooksRecyclerView.adapter as MyBooksRecyclerAdapter
    }
}
