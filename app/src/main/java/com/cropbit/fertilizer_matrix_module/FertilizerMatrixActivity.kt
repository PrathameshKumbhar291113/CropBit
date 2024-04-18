package com.cropbit.fertilizer_matrix_module

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cropbit.databinding.ActivityFertilizerMatrixBinding
import com.cropbit.utils.appStatusBarColor
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class FertilizerMatrixActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFertilizerMatrixBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFertilizerMatrixBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUi()
    }

    private fun setupUi() {
        appStatusBarColor(this, window)

        binding.btnAdd.setOnClickListener {
            binding.areaOfLandTextValue.text = String.format("%s",binding.areaOfLandTextValue.text.toString().toInt()+1)
        }

        binding.btnSubtract.setOnClickListener {
            binding.areaOfLandTextValue.text = String.format("%s",binding.areaOfLandTextValue.text.toString().toInt()-1)
        }

        binding.btnCheckFertility.setOnClickListener {
            calculateFertilizer()
        }
    }

    private fun calculateFertilizer() {
        val area = binding.areaOfLandTextValue.text.toString().toDoubleOrNull() ?: return
        val nitrogenPercentage = binding.editTextN.text.toString().toDoubleOrNull() ?: return
        val phosphorusPercentage = binding.editTextP.text.toString().toDoubleOrNull() ?: return
        val potassiumPercentage = binding.editTextK.text.toString().toDoubleOrNull() ?: return

        val nitrogenAmount = calculateNutrientAmount(area, nitrogenPercentage, "N")
        val phosphorusAmount = calculateNutrientAmount(area, phosphorusPercentage, "P")
        val potassiumAmount = calculateNutrientAmount(area, potassiumPercentage, "K")

        binding.resultForSoilFertilityTitle.text = "Result"
        // Display the suggested fertilizer quantities
        binding.resultForSoilFertilityValue.text = "Suggested Fertilizer Quantities:\n" +
                "Urea: ${nitrogenAmount.roundToInt()} kg\n" +
                "MOP: ${phosphorusAmount.roundToInt()} kg\n" +
                "DAP: ${potassiumAmount.roundToInt()} kg"
    }

    private fun calculateNutrientAmount(area: Double, percentage: Double, nutrient: String): Double {
        // Recommended nutrient amount per unit area (in kg per hectare)
        val recommendedAmounts = mapOf("N" to 50.0, "P" to 25.0, "K" to 30.0)
        return area * percentage / 100 * recommendedAmounts[nutrient]!!
    }
}