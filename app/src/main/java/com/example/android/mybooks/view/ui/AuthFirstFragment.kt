package com.example.android.mybooks.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.android.mybooks.BuildConfig
import com.example.android.mybooks.R
import com.example.android.mybooks.data.GoodreadsAuth
import com.github.scribejava.core.builder.ServiceBuilder
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AuthFirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.auth_fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            val authService = ServiceBuilder(BuildConfig.GOODREADS_API_KEY)
                .apiSecret("QR5Rnf7nDeJaE97RHBGnq7yjes1tNcKd7yn4I9A8AKA")
                .build(GoodreadsAuth())
            Flowable.fromCallable {
                return@fromCallable authService.getAuthorizationUrl(authService.requestToken)
            }.subscribeOn(Schedulers.io()).observeOn(Schedulers.single()).subscribe (
                {authUrl ->
                    val s = authUrl
                },
                {
                    val t = it
                }
            )

            //val accessToken = authService.getAccessToken(authService.requestToken, "verifier from callback")
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}
