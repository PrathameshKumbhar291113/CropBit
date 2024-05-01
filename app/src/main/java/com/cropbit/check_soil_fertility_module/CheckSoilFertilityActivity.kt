package com.cropbit.check_soil_fertility_module

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.cropbit.R
import com.cropbit.databinding.ActivityCheckSoilFertilityBinding
import com.cropbit.utils.appStatusBarColor
import com.cropbit.utils.calculateSoilFertilityAverage
import com.cropbit.utils.classifyFertilityValue
import dagger.hilt.android.AndroidEntryPoint

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

            val averageValue = calculateSoilFertilityAverage(
                binding.editTextph.text.toString().toFloat(),binding.editTextEc.text.toString().toFloat(),binding.editTextCalcium.text.toString().toFloat(), binding.editTextOrganic.text.toString().toFloat(),
                binding.editTextFerrous.text.toString().toFloat(), binding.editTextManganese.text.toString().toFloat(), binding.editTextCopper.text.toString().toFloat(), binding.editTextZinc.text.toString().toFloat(),
                binding.editTextN.text.toString().toFloat(), binding.editTextP.text.toString().toFloat(), binding.editTextK.text.toString().toFloat()
            )

            var classification = classifyFertilityValue(averageValue)
            binding.resultForSoilFertilityTitle.text = "Result"
            when(classification){
                "Bad" ->{
                    binding.resultForSoilFertilityValue.text = "Soil is infertile for harvesting more crops."
                    binding.resultForSoilFertilityValue.setTextColor(ContextCompat.getColor(this, R.color.red_dark_500))
                }
                "Normal" ->{
                    binding.resultForSoilFertilityValue.text = "Soil is fertile and in normal condition for harvesting more crops."
                    binding.resultForSoilFertilityValue.setTextColor(ContextCompat.getColor(this, splitties.material.colors.R.color.yellow_500))
                }
                "Good" -> {
                    binding.resultForSoilFertilityValue.text = "Soil is fertile and in good condition for harvesting more crops."
                    binding.resultForSoilFertilityValue.setTextColor(ContextCompat.getColor(this, R.color.primary_green))
                }
            }

        }
    }
}