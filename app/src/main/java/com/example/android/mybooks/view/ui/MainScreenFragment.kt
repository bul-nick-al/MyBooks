package com.example.android.mybooks.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.android.mybooks.R
import com.example.android.mybooks.databinding.MainScreenFragmentBinding
import com.example.android.mybooks.service.model.Book
import com.example.android.mybooks.view.adapter.BooksRecyclerAdapter
import com.example.android.mybooks.viewmodel.MainScreenViewModel

class MainScreenFragment : Fragment() {

    private lateinit var binding: MainScreenFragmentBinding

    companion object {
        fun newInstance() = MainScreenFragment()
    }

    private lateinit var viewModel: MainScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.main_screen_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(requireActivity()).get(MainScreenViewModel::class.java)

        viewModel.books.observe(viewLifecycleOwner, Observer { books ->  setBooks(books)})

        val adapter = BooksRecyclerAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(context);
        binding.recyclerView.adapter = adapter
        viewModel.loadBooks()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }

    private fun setBooks(dogs: List<Book>) {
        (binding.recyclerView.adapter as BooksRecyclerAdapter).setBooksList(dogs)
    }

}
