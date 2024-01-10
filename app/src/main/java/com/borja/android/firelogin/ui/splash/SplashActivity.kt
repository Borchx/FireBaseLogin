package com.borja.android.firelogin.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewbinding.ViewBinding
import com.borja.android.firelogin.R
import com.borja.android.firelogin.databinding.ActivitySplashBinding
import com.borja.android.firelogin.ui.detail.DetailActivity
import com.borja.android.firelogin.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val splashViewModel:SplashViewModel by viewModels()

    private lateinit var binding:ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        when(splashViewModel.checkDestination()){
            SplashDestination.Home -> navigateToHome()
            SplashDestination.Login -> navigateToLogin()
        }
    }

    private fun navigateToHome() {
        startActivity(Intent(this,DetailActivity::class.java))
    }

    private fun navigateToLogin(){
        startActivity(Intent(this,LoginActivity::class.java))
    }
}