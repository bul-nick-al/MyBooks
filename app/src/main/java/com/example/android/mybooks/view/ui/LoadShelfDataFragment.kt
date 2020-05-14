package com.example.android.mybooks.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.android.mybooks.BuildConfig
import com.example.android.mybooks.R
import com.example.android.mybooks.data.BookResponse
import com.example.android.mybooks.data.RestClient
import com.example.android.mybooks.data.UserBooksResponse
import com.example.android.mybooks.view.adapter.*
import com.example.android.mybooks.viewmodel.MainViewModel
import com.example.android.mybooks.viewmodel.MyBooksViewModelInterface
import kotlinx.android.synthetic.main.main_screen_fragment.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class LoadShelfDataFragment : Fragment() {
    private val mainViewModel: MainViewModel by activityViewModels()


    private val restClient: RestClient by inject {
        parametersOf(
            mainViewModel.accessToken.value,
            mainViewModel.accessToken.value
        )
    }

    fun getAdapter(): MyBooksRecyclerAdapter {
        val adapter = MyBooksRecyclerAdapter()

        adapter.clickListener = object : BookClickListener {
            override fun onBookClick(book: BookResponse?) {
                val bundle = Bundle()
                bundle.putInt("book_id", book!!.getBookId()!!)
                bundle.putString("book_title", book.getBookTitle())
                bundle.putString("book_image", book.getBookImageUrl())

                findNavController().navigate(R.id.action_global_bookOverviewFragment, bundle)
            }
        }

        return adapter
    }

    abstract fun getShelfName(): String;

    fun loadBooks() {
        restClient.goodreadsService.getBooksAtShelf(
            BuildConfig.GOODREADS_API_KEY,
            mainViewModel.userId.value!!,
            getShelfName()
        ).enqueue(
            object : Callback<UserBooksResponse> {
                override fun onFailure(call: Call<UserBooksResponse>, t: Throwable) {
                    throw t
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<UserBooksResponse>,
                    response: Response<UserBooksResponse>
                ) {
                    getScreenViewModel().getBooksList().value = response.body()?.reviews
                }

            }
        )
    }

    abstract fun getScreenViewModel(): MyBooksViewModelInterface

    protected abstract fun getBindingAdapter(): MyBooksRecyclerAdapter;

    protected fun setBooks(books: List<UserBooksResponse.Review>) {
        val myBooksRecyclerAdapter = getBindingAdapter()
        myBooksRecyclerAdapter.setBooksList(books)
    }
}