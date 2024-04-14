package com.cropbit.auth_module.presentation.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.cropbit.R
import com.cropbit.databinding.FragmentLogInBinding
import com.cropbit.home_module.presentation.HomeActivity
import com.cropbit.utils.togglePasswordVisibility
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import splitties.fragments.start

@AndroidEntryPoint
class LogInFragment : Fragment() {
    private lateinit var binding: FragmentLogInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleLoginClient: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogInBinding.inflate(inflater, container, false)

        firebaseAuth = FirebaseAuth.getInstance()
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()

        googleLoginClient = GoogleSignIn.getClient(requireContext(), googleSignInOptions)


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

        binding.signUpTextView.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }
        binding.googleSignInButton.setOnClickListener {
            signInGoogle()
//            firebaseAuth.signOut()
        }

        binding.emailIdEditText.addTextChangedListener {
            email = it.toString().trim()
        }

        binding.passwordEditText.addTextChangedListener {
            password = it.toString().trim()
        }

        binding.loginButton.setOnClickListener {
            /*firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    lifecycleScope.launch {
                        Toast.makeText(requireContext(), "Successfully LoggedIn.", Toast.LENGTH_SHORT).show()
                        delay(1000)
                        start<HomeActivity>()
                        requireActivity().finish()
                    }
                } else {
                    Toast.makeText(requireContext(), "${it.exception}", Toast.LENGTH_SHORT).show()
                }
            }*/

            start<HomeActivity>()
            requireActivity().finish()
        }
    }

    //Google authentication starts from here
    private fun signInGoogle() {
        val signInClient = googleLoginClient.signInIntent
        launcher.launch(signInClient)
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(activityResult.data)
                handleActivityResult(task)

            }
        }

    private fun handleActivityResult(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            val account: GoogleSignInAccount? = task.result
            if (account != null) {
                updateUI(account)
            }
        } else {
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {

        val credential = GoogleAuthProvider.getCredential(account.idToken, null)

        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {

            if (it.isSuccessful) {

                lifecycleScope.launch() {
                    Toast.makeText(requireContext(), "Successfully LoggedIn.", Toast.LENGTH_SHORT)
                        .show()
                    delay(1000)
                    start<HomeActivity>()
                    requireActivity().finish()
                }
            } else {

            }
        }
    }

}