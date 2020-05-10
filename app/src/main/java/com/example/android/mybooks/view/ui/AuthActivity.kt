package com.example.android.mybooks.view.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.Observer
import com.example.android.mybooks.R
import com.example.android.mybooks.di.appModule
import com.example.android.mybooks.viewmodel.AuthViewModel
import com.github.scribejava.core.model.OAuth1RequestToken
import com.github.scribejava.core.oauth.OAuth10aService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_auth.*
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin


class AuthActivity : AppCompatActivity() {

    private val authService: OAuth10aService by inject()
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            androidLogger()
            androidContext(this@AuthActivity)
            modules(appModule)
        }
        setContentView(R.layout.activity_auth)
        setSupportActionBar(toolbar)
        viewModel.requestToken.observe(
            this,
            Observer { requestToken ->
                var preferences =
                    getPreferences(Context.MODE_PRIVATE)
                preferences.edit {
                    putString("request_token_value", requestToken.token)
                    putString("request_token_secret", requestToken.tokenSecret)
                }
            }
        )
    }

    override fun onResume() {
        super.onResume()

        intent.data?.let { uri ->

            if (uri.toString().startsWith(resources.getString(R.string.redirect_scheme))) {
                // use the parameter your API exposes for the code (mostly it's "code")
                uri.getQueryParameter("oauth_token")?.let { token ->
                    val preferences =
                        getPreferences(Context.MODE_PRIVATE)
                    preferences.getString("request_token_secret", null)?.let { requestTokenSecret ->
                        preferences.getString("request_token_value", null)
                            ?.let { requestTokenValue ->
                                val requestToken =
                                    OAuth1RequestToken(requestTokenValue, requestTokenSecret)

                                Flowable.fromCallable {
                                    return@fromCallable authService.getAccessToken(requestToken, token)
                                }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                                    {
                                        val token = it
                                    },
                                    {
                                        val t = it
                                    }
                                )
                            }
                    }
                }
                /*if (code != null) {
                    // get access token
                    // we'll do that in a minute
                } else if (uri.getQueryParameter("error") != null) {
                    // show an error message here
                }*/
            }
        }
    }

    override fun onStop() {
        super.onStop()
        stopKoin()
    }

}
