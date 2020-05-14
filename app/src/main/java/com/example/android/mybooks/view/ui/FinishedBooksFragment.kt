package com.example.android.mybooks.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.android.mybooks.R
import com.example.android.mybooks.databinding.FinishedBooksFragmentBinding
import com.example.android.mybooks.view.adapter.MyBooksRecyclerAdapter
import com.example.android.mybooks.viewmodel.FinishedBooksViewModel
import com.example.android.mybooks.viewmodel.MyBooksViewModelInterface

class FinishedBooksFragment : LoadShelfDataFragment() {
    private lateinit var binding: FinishedBooksFragmentBinding
    val screenViewModel: FinishedBooksViewModel by activityViewModels()

    companion object {
        fun newInstance() = FinishedBooksFragment()
    }

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

        binding.finishedBooksRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        binding.finishedBooksRecyclerView.adapter = getAdapter()

        screenViewModel.books.observe(viewLifecycleOwner, Observer { books -> setBooks(books) })

        loadBooks()

        return binding.root
    }

    override fun getScreenViewModel(): MyBooksViewModelInterface {
        return screenViewModel
    }

    override fun getShelfName(): String {
        return "read"
    }

    override fun getBindingAdapter(): MyBooksRecyclerAdapter {
        return binding.finishedBooksRecyclerView.adapter as MyBooksRecyclerAdapter
    }
}
