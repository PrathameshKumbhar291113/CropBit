package com.cropbit.onboarding_module.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cropbit.R
import com.cropbit.databinding.FragmentOnBoardingScreen3Binding
import dagger.hilt.android.AndroidEntryPoint

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

    }
}