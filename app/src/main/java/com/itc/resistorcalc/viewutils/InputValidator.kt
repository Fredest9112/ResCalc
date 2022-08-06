package com.itc.resistorcalc.viewutils

import android.content.res.Resources
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.itc.resistorcalc.data.Constants.Companion.FOUR_BANDS_MIN_VALUE
import com.itc.resistorcalc.data.Constants.Companion.OTHERS_BANDS_MIN_VALUE
import com.google.android.material.textfield.TextInputEditText
import com.itc.resistorcalc.R
import com.itc.resistorcalc.data.Constants
import com.itc.resistorcalc.data.NoOfBands
import java.lang.Exception
import kotlin.properties.Delegates

object InputValidator {

    var isValidInput: Boolean = false

    fun checkInputValueToColor(
        noOfBands: NoOfBands,
        numbOnEditText: String,
        activity: FragmentActivity?,
        resources: Resources
    ): Long {
        var input by Delegates.notNull<Long>()
        if (numbOnEditText.isEmpty()) {
            input = 0L
            isValidInput = false
            return input
        } else {
            input = numbOnEditText.toLong()
            try {
                when (noOfBands) {
                    NoOfBands.FOUR_BANDS -> {
                        when {
                            input < FOUR_BANDS_MIN_VALUE -> {
                                Toast.makeText(
                                    activity,
                                    resources.getString(R.string.more_than_9_ohms_message),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            input > Constants.MAX_RESIST_VALUE -> {
                                Toast.makeText(
                                    activity,
                                    resources.getString(R.string.more_than_max_resistor_message),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            else -> {
                                isValidInput = true
                                return input
                            }
                        }
                    }
                    else -> {
                        when {
                            input < OTHERS_BANDS_MIN_VALUE -> {
                                Toast.makeText(
                                    activity,
                                    resources.getString(R.string.more_than_99_ohms_message),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            input > Constants.MAX_RESIST_VALUE -> {
                                Toast.makeText(
                                    activity,
                                    resources.getString(R.string.more_than_max_resistor_message),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            else -> {
                                isValidInput = true
                                return input
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(
                    activity,
                    resources.getString(R.string.error_input_message),
                    Toast.LENGTH_LONG
                ).show()
            }
            isValidInput = false
            return input
        }
    }

    fun checkInputColorToValue(
        input: TextInputEditText,
        activity: FragmentActivity?,
        resources: Resources
    ): Float {
        var value = 0.0F
        try {
            value = input.text.toString().toFloat()
            when {
                value > Constants.MAX_RESIST_VALUE -> {
                    Toast.makeText(
                        activity,
                        resources.getString(R.string.more_than_max_resistor_message),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    isValidInput = true
                    return value
                }
            }
        } catch (e: Exception) {
            Toast.makeText(
                activity,
                resources.getString(R.string.error_input_message),
                Toast.LENGTH_LONG
            ).show()
        }
        isValidInput = false
        return value
    }
}