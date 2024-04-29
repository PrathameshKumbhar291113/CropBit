package com.cropbit.crop_disease_diagnosis_module

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import coil.load
import com.cropbit.crop_disease_diagnosis_module.crop_disease_diagnosis_view_model.CropDiseaseDiagnosisViewModel
import com.cropbit.databinding.ActivityCropDiseaseDiagnosisBinding
import com.cropbit.utils.CropDiseaseList
import com.cropbit.utils.appStatusBarColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CropDiseaseDiagnosisActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCropDiseaseDiagnosisBinding

    private val PICK_IMAGE_REQUEST = 1
    private val PERMISSION_REQUEST_CODE = 2

    private val cropDiseaseDiagnosisViewModel : CropDiseaseDiagnosisViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCropDiseaseDiagnosisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUi()
        setUpObservers()

    }

    private fun setUpObservers() {
        cropDiseaseDiagnosisViewModel.imageUri.observe(this, Observer { newData ->
            binding.selectedCropImage.load(newData)
        })
    }

    private fun setupUi() {
        appStatusBarColor(this, window)

        binding.selectImage.setOnClickListener {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {

                if (ActivityCompat.checkSelfPermission(
                        this@CropDiseaseDiagnosisActivity,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    openImageChooser()
                }

            }else{
                if (ActivityCompat.checkSelfPermission(
                        this@CropDiseaseDiagnosisActivity,
                        Manifest.permission.MANAGE_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    openImageChooser()
                }
            }

            if (cropDiseaseDiagnosisViewModel.imageUri.value.toString().isNotEmpty()
                || cropDiseaseDiagnosisViewModel.imageUri.value.toString().isNotBlank()){
                binding.view.isVisible = false
                binding.selectedCropImage.isVisible = true
            }
        }

        binding.detectDisease.setOnClickListener {
            if (cropDiseaseDiagnosisViewModel.imageUri.value.toString().isNotEmpty()
                || cropDiseaseDiagnosisViewModel.imageUri.value.toString().isNotBlank()){
                var cropDiseaseName = CropDiseaseList().listOfDisease.random()
                binding.diseaseDetected.text = "Detected Diease"
                binding.diseaseOfCrop.text = cropDiseaseName
            }else{
                Toast.makeText(this, "Select Image Of Crop From The Device...", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openImageChooser() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openImageChooser()
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            data.data?.let {
                cropDiseaseDiagnosisViewModel.setImageUri(it.toString())
            }
        }
    }
}