package com.example.android.mybooks.view.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.mybooks.R
import com.example.android.mybooks.databinding.ReviewsScreenFragmentBinding
import com.example.android.mybooks.viewmodel.ReviewsScreenViewModel
import java.util.regex.Matcher
import java.util.regex.Pattern


class ReviewsScreenFragment : Fragment() {

    private lateinit var binding: ReviewsScreenFragmentBinding

    companion object {
        fun newInstance() = ReviewsScreenFragment()
    }

    private lateinit var viewModel: ReviewsScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.reviews_screen_fragment,
            container,
            false
        )

        activity?.let {
            if (it is AppCompatActivity) {
                it.setSupportActionBar(binding.customActionBar)
                it.supportActionBar?.setDisplayHomeAsUpEnabled(true)
                it.supportActionBar?.setDisplayShowHomeEnabled(true)
            }
        }

        viewModel = ViewModelProvider(requireActivity()).get(ReviewsScreenViewModel::class.java)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val reviewsHtml = requireArguments().getString("reviews_html")
        reviewsHtml?.let {
            val matcher: Matcher = Pattern.compile("src=\"([^\"]+)\"").matcher(it)
            matcher.find()
            val src = matcher.group(1)

            binding.reviewsWebView.loadUrl(src)

            binding.reviewsWebView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView,
                    url: String
                ): Boolean {
                    view.loadUrl(url);
                    return false
                }
            }

            binding.reviewsWebView.setOnKeyListener(object : View.OnKeyListener {
                override fun onKey(
                    v: View,
                    keyCode: Int,
                    event: KeyEvent
                ): Boolean {
                    if (event.action == KeyEvent.ACTION_DOWN) {
                        val webView = v as WebView
                        when (keyCode) {
                            KeyEvent.KEYCODE_BACK -> if (webView.canGoBack()) {
                                webView.goBack()
                                return true
                            }
                        }
                    }
                    return false
                }
            })
        }

//        reviewsHtml?.replace("width:565px;", "width:100%")
//        reviewsHtml?.replace(
//            "width=\"565\"",
//            "onload=\"this.width=screen.width;this.height=screen.height;\""
//        )
//
//        binding.reviewsWebView.setInitialScale(1)
//        binding.reviewsWebView.settings.javaScriptEnabled = true
//        binding.reviewsWebView.settings.loadWithOverviewMode = true;
//        binding.reviewsWebView.settings.useWideViewPort = true
//
//        binding.reviewsWebView.loadData(reviewsHtml, "text/html", "UTF-8")
        // TODO: Use the ViewModel
//        binding.customActionBar.setNavigationOnClickListener {
//            findNavController().popBackStack()
//        }
    }

}
