package com.example.android.mybooks.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.mybooks.BuildConfig

import com.example.android.mybooks.R
import com.example.android.mybooks.data.RestClient
import com.example.android.mybooks.data.UserBooksResponse
import com.example.android.mybooks.databinding.MainScreenFragmentBinding
import com.example.android.mybooks.service.model.Book
import com.example.android.mybooks.view.adapter.BooksRecyclerAdapter
import com.example.android.mybooks.viewmodel.MainScreenViewModel
import com.example.android.mybooks.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.action_bar.*
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
    private val restClient: RestClient by inject {  parametersOf(null, null) }

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

        screenViewModel.books.observe(viewLifecycleOwner, Observer { books ->  setBooks(books)})

        val adapter = BooksRecyclerAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(context);
        binding.recyclerView.adapter = adapter
        screenViewModel.loadBooks()
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
                        val books = response.body()?.reviews
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

    private fun setBooks(dogs: List<Book>) {
        //(binding.recyclerView.adapter as BooksRecyclerAdapter).setBooksList(dogs)
    }

}
