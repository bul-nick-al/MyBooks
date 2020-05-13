package com.example.android.mybooks.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.mybooks.BuildConfig

import com.example.android.mybooks.R
import com.example.android.mybooks.data.BookResponse
import com.example.android.mybooks.data.RestClient
import com.example.android.mybooks.data.SearchBooksResponse
import com.example.android.mybooks.databinding.AllBooksScreenFragmentBinding
import com.example.android.mybooks.view.adapter.AllBooksRecyclerAdapter
import com.example.android.mybooks.view.adapter.BookClickListener
import com.example.android.mybooks.viewmodel.AllBooksScreenViewModel
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllBooksScreenFragment : Fragment() {

    private lateinit var binding: AllBooksScreenFragmentBinding
    private val restClient: RestClient by inject { parametersOf(null, null) }

    companion object {
        fun newInstance() = AllBooksScreenFragment()
    }

    private lateinit var viewModel: AllBooksScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.all_books_screen_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(requireActivity()).get(AllBooksScreenViewModel::class.java)

        viewModel.books.observe(viewLifecycleOwner, Observer { books -> setBooks(books) })

        val adapter = AllBooksRecyclerAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(context);
        binding.recyclerView.adapter = adapter

        adapter.clickListener = object : BookClickListener {
            override fun onBookClick(book: BookResponse?) {
                book?.let {
                    val bundle = Bundle()
                    bundle.putInt("book_id", it.getBookId()!!)
                    bundle.putString("book_title", it.getBookTitle())
                    bundle.putString("book_image", it.getBookImageUrl())

                    findNavController().navigate(R.id.action_global_bookOverviewFragment, bundle)
                }
            }
        }

        binding.booksSearchBar.onSearch = {
            restClient.goodreadsService.searchBooks(BuildConfig.GOODREADS_API_KEY, it).enqueue(
                object : Callback<SearchBooksResponse> {
                    override fun onFailure(call: Call<SearchBooksResponse>, t: Throwable) {
                        Toast.makeText(
                            context,
                            resources.getString(R.string.error_cannot_load_books),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onResponse(
                        call: Call<SearchBooksResponse>,
                        response: Response<SearchBooksResponse>
                    ) {
                        val books = response.body()
                        books?.search?.results?.let {
                            val books = it.mapNotNull {
                                it?.bestBook
                            }
                            viewModel.books.value = books
                        }
                    }

                }
            )
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    private fun setBooks(books: List<SearchBooksResponse.Search.Work.BestBook>) {
        (binding.recyclerView.adapter as AllBooksRecyclerAdapter).setBooksList(books)
    }

}
