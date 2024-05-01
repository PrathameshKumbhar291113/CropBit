package com.cropbit.home_module.presentation.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.cropbit.SplashActivity
import com.cropbit.databinding.FragmentProfileBinding
import com.cropbit.home_module.presentation.PrivacyPolicyActivity
import com.cropbit.utils.BundleConstants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import splitties.fragments.start


@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       setupUi()
    }

    private fun setupUi() {

        binding.incPrivacySecurity.settingPrivacyDetails.setOnClickListener {
            start<PrivacyPolicyActivity>()
        }
        binding.incPrivacySecurity.ivPrivacyNext.setOnClickListener {
            start<PrivacyPolicyActivity>()
        }

        binding.logoutContainer.setOnClickListener {

            lifecycleScope.launch() {
                Toast.makeText(requireContext(), "Successfully LoggedOut.", Toast.LENGTH_SHORT)
                    .show()
                delay(1000)
                start<SplashActivity>() {
                    val sharePrefLogin: SharedPreferences =
                        requireContext().getSharedPreferences(BundleConstants.LOGIN, Context.MODE_PRIVATE)
                    var editor : SharedPreferences.Editor = sharePrefLogin.edit()
                    editor.putBoolean(BundleConstants.IS_LOGIN_COMPLETED,false)
                    editor.apply()
                    requireActivity().finish()
                }
            }

        }

    }
}