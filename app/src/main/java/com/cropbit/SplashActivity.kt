package com.cropbit

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.cropbit.databinding.ActivitySplashBinding
import com.cropbit.home_module.presentation.HomeActivity
import com.cropbit.onboarding_module.presentation.OnboardingActivity
import com.cropbit.utils.BundleConstants
import com.cropbit.utils.appStatusBarColor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import splitties.activities.start

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupUi()
    }

    private fun setupUi() {

        appStatusBarColor(this, window)

        val sharePrefLogin: SharedPreferences =
            getSharedPreferences(BundleConstants.LOGIN, Context.MODE_PRIVATE)

        var isLoginCompleted = sharePrefLogin.getBoolean(BundleConstants.IS_LOGIN_COMPLETED, false)

        lifecycleScope.launch {
            delay(5000)
            if (isLoginCompleted){
                start<HomeActivity>()
            }else{
                start<OnboardingActivity>()
            }
            finish()
        }
    }
}