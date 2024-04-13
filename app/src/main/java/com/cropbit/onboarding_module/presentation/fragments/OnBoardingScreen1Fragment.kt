package com.cropbit.onboarding_module.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cropbit.R
import com.cropbit.auth_module.presentation.AuthActivity
import com.cropbit.databinding.FragmentOnBoardingScreen1Binding
import dagger.hilt.android.AndroidEntryPoint
import splitties.fragments.start

@AndroidEntryPoint
class OnBoardingScreen1Fragment : Fragment() {
    private lateinit var binding: FragmentOnBoardingScreen1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        binding = FragmentOnBoardingScreen1Binding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUi()

    }

    private fun setUpUi() {
        binding.nextButton.setOnClickListener {
            findNavController().navigate(R.id.onBoardingScreen2Fragment)
        }

        binding.skipText.setOnClickListener {
            start<AuthActivity>()
            requireActivity().finish()
        }
    }
}