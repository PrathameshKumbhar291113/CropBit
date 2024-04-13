package com.cropbit.auth_module.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.cropbit.databinding.FragmentRegisterBinding
import com.cropbit.utils.togglePasswordVisibility
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        firebaseAuth = FirebaseAuth.getInstance()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupUi() {
        var email: String = ""
        var password: String = ""

        binding.passwordEditText.setOnTouchListener { _, event ->
            val drawableEnd = binding.passwordEditText.compoundDrawables[2]
            if (event.action == MotionEvent.ACTION_UP && drawableEnd != null && event.rawX >= (binding.passwordEditText.right - drawableEnd.bounds.width() - binding.passwordEditText.compoundDrawablePadding)) {
                togglePasswordVisibility(requireContext(), binding.passwordEditText)
                true
            } else {
                false
            }
        }

        binding.confirmPasswordEditText.setOnTouchListener { _, event ->
            val drawableEnd = binding.confirmPasswordEditText.compoundDrawables[2]
            if (event.action == MotionEvent.ACTION_UP && drawableEnd != null && event.rawX >= (binding.confirmPasswordEditText.right - drawableEnd.bounds.width() - binding.confirmPasswordEditText.compoundDrawablePadding)) {
                togglePasswordVisibility(requireContext(), binding.confirmPasswordEditText)
                true
            } else {
                false
            }
        }

        binding.emailIdEditText.addTextChangedListener {
            email = it.toString().trim()
        }

        binding.passwordEditText.addTextChangedListener {
            password = it.toString().trim()
        }

        binding.confirmPasswordEditText.addTextChangedListener {
            if (password.matches(it.toString().trim().toRegex())){
                binding.confirmPasswordEditText.setTextColor(ContextCompat.getColor(requireContext(), splitties.material.colors.R.color.green_400))
            }else{
                binding.confirmPasswordEditText.setTextColor(ContextCompat.getColor(requireContext(), splitties.material.colors.R.color.red_500))
            }
        }

        binding.signInTextView.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.registerButton.setOnClickListener {

            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        lifecycleScope.launch {
                            Toast.makeText(requireContext(), "Registered Successfully", Toast.LENGTH_SHORT).show()
                            delay(2000)
                           findNavController().popBackStack()
                        }
                    } else {

                    }
                }

        }
    }
}