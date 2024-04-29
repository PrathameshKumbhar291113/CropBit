package com.cropbit.crop_disease_diagnosis_module.crop_disease_diagnosis_view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CropDiseaseDiagnosisViewModel @Inject constructor() : ViewModel() {

    var imageUri = MutableLiveData<String>("")

    fun setImageUri(uriString: String){
        imageUri.value = uriString
    }
}