package com.cropbit.onboarding_module.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cropbit.auth_module.presentation.AuthActivity
import com.cropbit.databinding.FragmentOnBoardingScreen3Binding
import dagger.hilt.android.AndroidEntryPoint
import splitties.fragments.start

@AndroidEntryPoint
class OnBoardingScreen3Fragment : Fragment() {
    private lateinit var binding: FragmentOnBoardingScreen3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingScreen3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
    }

    private fun setupUi() {
        binding.nextButton.setOnClickListener {
            start<AuthActivity>()
        }

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.skipText.setOnClickListener {
            start<AuthActivity>()
            requireActivity().finish()
        }
    }
}