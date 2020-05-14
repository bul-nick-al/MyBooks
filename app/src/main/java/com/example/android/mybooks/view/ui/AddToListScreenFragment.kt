package com.example.android.mybooks.view.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.mybooks.BuildConfig

import com.example.android.mybooks.R
import com.example.android.mybooks.data.RestClient
import com.example.android.mybooks.data.ShelvesListResponse
import com.example.android.mybooks.data.UserBooksResponse
import com.example.android.mybooks.databinding.AddToListScreenFragmentBinding
import com.example.android.mybooks.view.adapter.ShelfClickListener
import com.example.android.mybooks.view.adapter.ShelvesListRecyclerAdapter
import com.example.android.mybooks.viewmodel.AddToListScreenViewModel
import com.example.android.mybooks.viewmodel.MainViewModel
import okhttp3.ResponseBody
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddToListScreenFragment : Fragment() {

    private lateinit var binding: AddToListScreenFragmentBinding
    private lateinit var viewModel: AddToListScreenViewModel
    private val mainViewModel: MainViewModel by activityViewModels()
    private val restClient: RestClient by inject {
        parametersOf(
            mainViewModel.accessToken.value,
            mainViewModel.accessToken.value
        )
    }

    companion object {
        fun newInstance() = AddToListScreenFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.add_to_list_screen_fragment,
            container,
            false
        )

        activity?.let {
            if (it is AppCompatActivity) {
                (activity as AppCompatActivity?)!!.setSupportActionBar(binding.customActionBar)
                (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayHomeAsUpEnabled(true)
                (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayShowHomeEnabled(true)
            }
        }


        viewModel = ViewModelProvider(requireActivity()).get(AddToListScreenViewModel::class.java)

        val bookTitle = requireArguments().getString("book_title")
        val bookId = requireArguments().getInt("book_id")

        val adapter = ShelvesListRecyclerAdapter()

        adapter.clickListener = object : ShelfClickListener {
            override fun onShelfClick(shelfName: String, isDelete: Boolean) {
                removeFromShelf(shelfName, isDelete, bookId, bookTitle!!)
            }
        }

        binding.addToListShelves.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        binding.addToListShelves.adapter = adapter

        loadUserShelves(bookTitle!!)

        return binding.root
    }

    fun removeFromShelf(shelfName: String, isDelete: Boolean, bookId: Int, bookTitle: String) {
        var deleteString: String = ""

        if (isDelete) {
            deleteString = "remove"
            Toast.makeText(
                context,
                resources.getString(R.string.removing_from_shelf),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                context,
                resources.getString(R.string.adding_to_shelf),
                Toast.LENGTH_SHORT
            ).show()
        }

        restClient.goodreadsService.addToShelf(
            BuildConfig.GOODREADS_API_KEY,
            shelfName,
            bookId,
            deleteString
        ).enqueue(
            object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    throw t
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val responseBody = response.body()?.string()
                    Toast.makeText(
                        context,
                        resources.getString(R.string.success),
                        Toast.LENGTH_SHORT
                    ).show()

                    loadUserBooks(bookTitle)
                }

            }
        )
    }

    fun loadUserBooks(bookTitle: String) {
        restClient.goodreadsService.getUserBooks(
            BuildConfig.GOODREADS_API_KEY,
            mainViewModel.userId.value!!,
            bookTitle
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
                    viewModel.books.value = response.body()?.reviews
                }

            }
        )
    }

    fun loadUserShelves(bookTitle: String) {
        restClient.goodreadsService.getUserShelves(
            BuildConfig.GOODREADS_API_KEY,
            mainViewModel.userId.value!!
        ).enqueue(
            object : Callback<ShelvesListResponse> {
                override fun onFailure(call: Call<ShelvesListResponse>, t: Throwable) {
                    throw t
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<ShelvesListResponse>,
                    response: Response<ShelvesListResponse>
                ) {
                    viewModel.shelves.value = response.body()?.shelves?.userShelves

                    loadUserBooks(bookTitle)
                }

            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            if (it is AppCompatActivity) {
                it.setSupportActionBar(view.findViewById(R.id.bookOverviewRealToolbar))
                it.supportActionBar?.setDisplayHomeAsUpEnabled(true)
                it.supportActionBar?.setDisplayShowHomeEnabled(true)
            }
        }

        viewModel.shelves.observe(
            viewLifecycleOwner,
            Observer { shelves -> setShelvesLists(shelves) })

        viewModel.books.observe(
            viewLifecycleOwner,
            Observer { reviews -> setCurrentList(reviews) })
    }

    fun setShelvesLists(shelves: List<ShelvesListResponse.Shelves.UserShelf>) {
        val adapter = binding.addToListShelves.adapter as ShelvesListRecyclerAdapter
        adapter.setShelvesList(shelves)
    }

    fun setCurrentList(reviews: List<UserBooksResponse.Review>) {
        val adapter = binding.addToListShelves.adapter as ShelvesListRecyclerAdapter

        if (reviews.size == 0) {
            adapter.setActiveShelf(null)
            return
        }

        val review = reviews[0]

        review.shelves?.let { shelvesWrapper ->
            shelvesWrapper.shelves?.let { shelves ->
                if (shelves.size === 0) {
                    adapter.setActiveShelf(null)
                    return;
                }

                val shelve = shelves[0]

                shelve.name?.let {
                    adapter.setActiveShelf(it)
                }
            }
        }
    }

}