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
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.android.mybooks.R
import com.example.android.mybooks.databinding.CurrentBooksFragmentBinding
import com.example.android.mybooks.databinding.PlannedBooksFragmentBinding
import com.example.android.mybooks.view.adapter.MyBooksRecyclerAdapter
import com.example.android.mybooks.viewmodel.MyBooksViewModelInterface
import com.example.android.mybooks.viewmodel.PlannedBooksViewModel

class PlannedBooksFragment : LoadShelfDataFragment() {
    private lateinit var binding: PlannedBooksFragmentBinding
    val screenViewModel: PlannedBooksViewModel by activityViewModels()

    companion object {
        fun newInstance() = FinishedBooksFragment()
    }

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

        binding.plannedBooksRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        binding.plannedBooksRecyclerView.adapter = getAdapter()

        screenViewModel.books.observe(viewLifecycleOwner, Observer { books -> setBooks(books) })

        loadBooks()

        return binding.root
    }

    override fun getScreenViewModel(): MyBooksViewModelInterface {
        return screenViewModel
    }

    override fun getShelfName(): String {
        return "to-read"
    }

    override fun getBindingAdapter(): MyBooksRecyclerAdapter {
        return binding.plannedBooksRecyclerView.adapter as MyBooksRecyclerAdapter
    }
}
