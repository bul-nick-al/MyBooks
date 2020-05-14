package com.example.android.mybooks.view.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.android.mybooks.R
import com.example.android.mybooks.data.RestClient
import com.example.android.mybooks.data.UserIdResponse
import com.example.android.mybooks.di.appModule
import com.example.android.mybooks.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.parameter.parametersOf
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(customActionBar)
        setUpNavigation()

        val token = intent.getStringExtra("token")
        val secret = intent.getStringExtra("secret")

        val viewModel: MainViewModel by viewModels()
        viewModel.accessToken.value = token
        viewModel.accessTokenSecret.value = secret

        val restClient: RestClient by inject { parametersOf(token, secret) }
        restClient.goodreadsService.getUserId().enqueue(
            object : Callback<UserIdResponse> {
                override fun onFailure(call: Call<UserIdResponse>, t: Throwable) {
                    val t = t
                }

                override fun onResponse(
                    call: Call<UserIdResponse>,
                    response: Response<UserIdResponse>
                ) {
                    viewModel.userId.value = response.body()?.user?.id
                }

            }
        )
    }

    fun setUpNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        NavigationUI.setupWithNavController(
            bottomNavigationView,
            navHostFragment!!.navController
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return true
    }
}
