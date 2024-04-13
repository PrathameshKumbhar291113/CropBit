package com.cropbit.auth_module.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cropbit.databinding.ActivityAuthBinding
import com.cropbit.utils.appStatusBarColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupUi()

    }

    private fun setupUi() {
        appStatusBarColor(this, window)
    }
}