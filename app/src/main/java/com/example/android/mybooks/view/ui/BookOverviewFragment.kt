package com.example.android.mybooks.view.ui

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.android.mybooks.BuildConfig
import com.example.android.mybooks.R
import com.example.android.mybooks.data.BookDetailsResponse
import com.example.android.mybooks.data.RestClient
import com.example.android.mybooks.databinding.BookOverviewFragmentBinding
import com.example.android.mybooks.viewmodel.BookOverviewViewModel
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookOverviewFragment : Fragment() {

    private lateinit var binding: BookOverviewFragmentBinding
    private val restClient: RestClient by inject { parametersOf(null, null) }

//    val args: BookOverviewFragmentArgs by navArgs()

    companion object {
        fun newInstance() = BookOverviewFragment()
    }

    private lateinit var viewModel: BookOverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.book_overview_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(requireActivity()).get(BookOverviewViewModel::class.java)

        val bookId = requireArguments().getInt("book_id")

        restClient.goodreadsService
            .getBookDetails(BuildConfig.GOODREADS_API_KEY, bookId)
            .enqueue(
                object : Callback<BookDetailsResponse> {
                    override fun onFailure(call: Call<BookDetailsResponse>, t: Throwable) {
                        throw t
                        TODO("Not yet implemented")
                    }

                    override fun onResponse(
                        call: Call<BookDetailsResponse>,
                        response: Response<BookDetailsResponse>
                    ) {
                        viewModel.book.value = response.body()?.book
                    }
                }
            )

        return binding.root
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

        binding.bookOverviewToolbar.setExpandedTitleTextAppearance(R.style.CollapsedAppBar)
        binding.bookOverviewToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.colorWhite))
        binding.bookOverviewToolbar.title = requireArguments().getString("book_title")

        viewModel.book.observe(viewLifecycleOwner, Observer {
            binding.bookOverviewToolbar.title = it.title
            it.description?.let { description ->
                binding.bookOverviewDescription.text = Html.fromHtml(description)
            }

            it.ratingsCount?.let { ratingsCount ->
                binding.bookOverviewRating.text = resources.getQuantityString(
                    R.plurals.book_overview_rating_text, //plural from strings.xml file
                    ratingsCount, //quantity
                    it.averageRating, //var arg - first parameter
                    ratingsCount //var arg - second parameter
                )
            }

            Glide.with(view.context)
                .load(it.imageUrl)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.bookOverviewCover.resetLoader()
                        return false
                    }
                })
                .fitCenter()
                .into(binding.bookOverviewCover)

            binding.shareBookButton.setOnClickListener { view ->
                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                sharingIntent.putExtra(Intent.EXTRA_TEXT, it.link)
                startActivity(
                    Intent.createChooser(
                        sharingIntent,
                        resources.getString(R.string.share_book)
                    )
                )
            }

        });

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(BookOverviewViewModel::class.java)

        binding.addToListButton.setOnClickListener {
            findNavController().navigate(R.id.action_bookOverviewFragment_to_addToListScreenFragment)
        }
        binding.leaveReviewButton.setOnClickListener {
            findNavController().navigate(R.id.action_bookOverviewFragment_to_leaveReviewScreenFragment)
        }
        binding.readReviewsButton.setOnClickListener {
            findNavController().navigate(R.id.action_bookOverviewFragment_to_reviewsScreenFragment)
        }

    }

}
