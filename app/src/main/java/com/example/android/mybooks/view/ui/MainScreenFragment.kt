package com.example.android.mybooks.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.android.mybooks.BuildConfig
import com.example.android.mybooks.R
import com.example.android.mybooks.data.BookResponse
import com.example.android.mybooks.data.RestClient
import com.example.android.mybooks.data.UserBooksResponse
import com.example.android.mybooks.databinding.MainScreenFragmentBinding
import com.example.android.mybooks.view.adapter.BookClickListener
import com.example.android.mybooks.view.adapter.OwnedBooksRecyclerAdapter
import com.example.android.mybooks.viewmodel.MainScreenViewModel
import com.example.android.mybooks.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_screen_fragment.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainScreenFragment : Fragment() {

    private lateinit var binding: MainScreenFragmentBinding

    companion object {
        fun newInstance() = MainScreenFragment()
    }

    private val screenViewModel: MainScreenViewModel by activityViewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    private val restClient: RestClient by inject { parametersOf(null, null) }

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

        screenViewModel.books.observe(viewLifecycleOwner, Observer { books -> setBooks(books) })

        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerView.adapter = OwnedBooksRecyclerAdapter()
        mainViewModel.userId.observe(viewLifecycleOwner, Observer { id ->
            restClient.goodreadsService.getUserBooks(BuildConfig.GOODREADS_API_KEY, id).enqueue(
                object : Callback<UserBooksResponse> {
                    override fun onFailure(call: Call<UserBooksResponse>, t: Throwable) {
                        throw t
                        TODO("Not yet implemented")
                    }

                    override fun onResponse(
                        call: Call<UserBooksResponse>,
                        response: Response<UserBooksResponse>
                    ) {
                        screenViewModel.books.value = response.body()?.reviews
                    }

                }
            )

        })

//        (activity as AppCompatActivity?)!!.setSupportActionBar(actionBar)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }

    private fun setBooks(books: List<UserBooksResponse.Review>) {
        val currentBook = books.find { book ->
            book.startedAt != null && book.readAt == null
        }
        currentBook?.let {
            bookTitle.text = resources.getString(R.string.home_reading, it.book?.title)
            Glide.with(requireContext()).load(it.book?.imageUrl)
                .placeholder(R.drawable.book_cover).fitCenter().into(bookCover)
        }

        val ownedBooksAdapter = binding.recyclerView.adapter as OwnedBooksRecyclerAdapter
        ownedBooksAdapter.clickListener = object : BookClickListener {
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

        ownedBooksAdapter.setBooksList(books)
    }

}
