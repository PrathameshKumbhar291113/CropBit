package com.cropbit.check_soil_fertility_module

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.cropbit.R
import com.cropbit.databinding.ActivityCheckSoilFertilityBinding
import com.cropbit.utils.appStatusBarColor
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class CheckSoilFertilityActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckSoilFertilityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCheckSoilFertilityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUi()
    }

    private fun setupUi() {
        appStatusBarColor(this, window)
        binding.btnCheckFertility.setOnClickListener {
            var isSoilFertile = isSoilFertile(binding.editTextN.text.toString().toDouble(), binding.editTextP.text.toString().toDouble(), binding.editTextK.text.toString().toDouble())
            if (isSoilFertile){
                binding.resultForSoilFertilityTitle.text = "Result"
                binding.resultForSoilFertilityValue.text = "Soil is fertile and in good condition for harvesting more crops."
                binding.resultForSoilFertilityValue.setTextColor(ContextCompat.getColor(this, R.color.primary_green))
            }else{
                binding.resultForSoilFertilityTitle.text = "Result"
                binding.resultForSoilFertilityValue.text = "Soil is infertile for harvesting more crops."
                binding.resultForSoilFertilityValue.setTextColor(ContextCompat.getColor(this, R.color.red_dark_500))
            }
        }
    }

    private fun isSoilFertile(nitrogen: Double, phosphorus: Double, potassium: Double): Boolean {
        val idealRatio1 = listOf(4.0, 2.0, 1.0)
        val idealRatio2 = listOf(3.0, 1.0, 2.0)

        val actualRatio = listOf(nitrogen, phosphorus, potassium)

        val tolerance = 0.1
        val isRatio1 = actualRatio.zip(idealRatio1).all { (actual, ideal) ->
            abs(actual / ideal - 1.0) < tolerance
        }

        val isRatio2 = actualRatio.zip(idealRatio2).all { (actual, ideal) ->
            abs(actual / ideal - 1.0) < tolerance
        }

        return isRatio1 || isRatio2
    }
}