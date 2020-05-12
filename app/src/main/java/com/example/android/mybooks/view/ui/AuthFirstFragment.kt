package com.example.android.mybooks.view.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.mybooks.BuildConfig
import com.example.android.mybooks.R
import com.example.android.mybooks.data.GoodreadsAuth
import com.example.android.mybooks.viewmodel.AuthViewModel
import com.github.scribejava.core.builder.ServiceBuilder
import com.github.scribejava.core.oauth.OAuth10aService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.android.ext.android.inject


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AuthFirstFragment : Fragment() {

    private val authService: OAuth10aService by inject()
    private val viewModel: AuthViewModel by activityViewModels()

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
            Flowable.fromCallable {
                val requestToken = authService.requestToken
                return@fromCallable Pair(authService.getAuthorizationUrl(requestToken), requestToken)
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe (
                {(authUrl, requestToken) ->
                    viewModel.setToken(requestToken)
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(authUrl)
                    )
                    startActivity(intent)
                },
                {
                    val t = it
                }
            )

            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}
