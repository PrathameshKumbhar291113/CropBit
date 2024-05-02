package com.cropbit.home_module.presentation

import android.net.http.SslError
import android.os.Bundle
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.cropbit.databinding.ActivityPrivacyPolicyBinding
import com.cropbit.utils.appStatusBarColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PrivacyPolicyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrivacyPolicyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPrivacyPolicyBinding.inflate(layoutInflater)

        setContentView(binding.root)

        appStatusBarColor(this, window)

        configureWebView()
    }

    private fun configureWebView() {

        binding.privacyPolicyWebView.clearCache(true)

        binding.privacyPolicyWebView.setWebViewClient(object : WebViewClient() {
            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                handler?.proceed()
            }
        })

        val privacyPolicyUrl = "https://sites.google.com/view/cropbitapp/privacy-policy"
        binding.privacyPolicyWebView.loadUrl(privacyPolicyUrl)
        binding.privacyPolicyWebView.settings.javaScriptEnabled = true
        binding.privacyPolicyWebView.settings.setSupportZoom(true)
    }
}