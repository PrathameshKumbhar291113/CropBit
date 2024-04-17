package com.cropbit.home_module.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.cropbit.R
import com.cropbit.databinding.ActivityHomeBinding
import com.cropbit.utils.appStatusBarColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    private var doubleBackToExitPressedOnce = false

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            when (navController.currentBackStackEntry?.destination?.id) {
                R.id.homeFragment -> {

                    if (doubleBackToExitPressedOnce) {
                        finish()
                        return
                    }
                    doubleBackToExitPressedOnce = true
                    Toast.makeText(this@HomeActivity,"Please click back again to exit", Toast.LENGTH_SHORT).show()
                    Handler(Looper.getMainLooper()).postDelayed({
                        doubleBackToExitPressedOnce = false
                    }, 2000)

                }

                R.id.bitBotFragment -> {
                    navController.popBackStack(R.id.homeFragment, inclusive = true)
                }

                R.id.profileFragment -> {
                    navController.popBackStack(R.id.homeFragment, inclusive = true)
                }

                else -> {
                    if (navController.navigateUp().not()) {
                        finish()
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupUi()

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)

        onBackPressedDispatcher.addCallback(callback)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.homeFragment -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }

                R.id.bitBotFragment -> {
                    navController.navigate(R.id.bitBotFragment)
                    true
                }

                R.id.profileFragment -> {
                    navController.navigate(R.id.profileFragment)
                    true
                }

                else -> {
                    false
                }


            }
        }

        binding.bottomNavigationView.setOnItemReselectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    /*setfragment(HOmeFragment())
                    * val frasgment=1*/
                    navController.navigate(R.id.homeFragment)
                }

                R.id.bitBotFragment -> {
                    /*setfragment(homeFragment)
                    * val frasgment=2*/
                    navController.navigate(R.id.bitBotFragment)
                }

                R.id.profileFragment -> {
                    navController.navigate(R.id.profileFragment)
                }

                else -> {
                }
            }
        }

        navController.addOnDestinationChangedListener{ _, destination, _ ->
            when(destination.id){
                R.id.homeFragment -> {

                }

                R.id.bitBotFragment -> {

                }

                R.id.profileFragment -> {

                }

                else -> {
                }
            }
        }

    }
    private fun setupUi() {
        appStatusBarColor(this, window)
    }

}