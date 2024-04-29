package com.cropbit.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.method.PasswordTransformationMethod
import android.view.Window
import android.widget.EditText
import com.cropbit.R
import kotlin.math.floor

fun appStatusBarColor(context: Context, window: Window) {
    window.statusBarColor = context.resources.getColor(R.color.primary_green)
}

fun togglePasswordVisibility(context: Context, editText: EditText) {
    val selection = editText.selectionStart // Save the cursor position
    if (editText.transformationMethod == null) {
        // Password is visible, hide it
        editText.transformationMethod = PasswordTransformationMethod.getInstance()
        val hideDrawable: Drawable? = context.resources.getDrawable(R.drawable.iv_password_hide)
        editText.setCompoundDrawablesWithIntrinsicBounds(
            editText.compoundDrawables[0],
            editText.compoundDrawables[1],
            hideDrawable,
            editText.compoundDrawables[3]
        )
    } else {
        // Password is hidden, show it
        editText.transformationMethod = null
        val showDrawable: Drawable? = context.resources.getDrawable(R.drawable.iv_password_show)
        editText.setCompoundDrawablesWithIntrinsicBounds(
            editText.compoundDrawables[0],
            editText.compoundDrawables[1],
            showDrawable,
            editText.compoundDrawables[3]
        )
    }
    // Restore the cursor position
    editText.setSelection(selection)
}

data class CropDiseaseList(
    var listOfDisease: List<String> = listOf(
        "Powdery Mildew",
        "Downy Mildew",
        "Black Spot",
        "Mosaic Virus",
        "Fusarium Wilt",
        "Verticillium Wilt",
        "Sooty Mold",
        "Snow Mold",
        "Rust"
    )
)


fun convertToScaledValue(amount: Double): Double {
    return floor(amount/ 10.0)
}