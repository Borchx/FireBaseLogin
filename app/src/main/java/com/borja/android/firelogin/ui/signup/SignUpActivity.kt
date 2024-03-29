package com.borja.android.firelogin.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.borja.android.firelogin.R
import com.borja.android.firelogin.databinding.ActivitySignUpBinding
import com.borja.android.firelogin.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private val signUpViewModel: SignUpViewModel by viewModels()

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initListeners()
        initUIState()
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                signUpViewModel.isLoading.collect {
                    binding.loading.isVisible = it
                }
            }
        }
    }

    private fun initListeners() {
        binding.btnSignUp.setOnClickListener {
            signUpViewModel.register(
                email = binding.tieUser.text.toString(),
                password = binding.tiePassword.text.toString()
            ) { navigateToDetail() }
        }
    }



    private fun navigateToDetail() {
        startActivity(Intent(this,DetailActivity::class.java))
    }
}