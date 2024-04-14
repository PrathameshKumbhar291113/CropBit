package com.cropbit.home_module.presentation.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.cropbit.databinding.FragmentHomeBinding
import com.cropbit.home_module.presentation.view_model.HomeViewModel
import com.cropbit.utils.NetworkResult
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupObservers()
        fetchLocation()
    }

    private fun setupObservers() {


        homeViewModel.currentWeatherResponse.observe(viewLifecycleOwner){
            when(it){
                is NetworkResult.Loading ->{}
                is NetworkResult.Success ->{
                    binding.weatherForecastIcon.load("https:${it.data?.body()?.current?.condition?.icon.toString()}")
                    Log.e("weather", "setupObservers: ${it.data?.body()?.current?.condition?.icon.toString()}")
                    binding.temperatureTextView.text = "${it.data?.body()?.current?.tempC.toString()}°C"
                    binding.conditionTextView.text = it.data?.body()?.current?.condition?.text.toString()
                    binding.locationTextView.text = it.data?.body()?.location?.name.toString()
                }
                is NetworkResult.Error ->{}
            }
        }

    }

    private fun setupUi() {

    }

    private fun fetchLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude

                    homeViewModel.getCurrentWeatherForecast(latitude, longitude)

                    Log.e("Location", "Latitude: ${latitude.toString()}, Longitude: ${longitude.toString()}")
                    Toast.makeText(requireContext(), "Latitude: $latitude, Longitude: $longitude", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(requireContext(), "Enable GPS To Fetch Location.", Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener { e ->
                Log.e("Location", "Error: ${e.message}")
            }
    }

}