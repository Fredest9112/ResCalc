package com.itc.resistorcalc.model.fromvalueresistor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.itc.resistorcalc.data.resistor.IResistor

@Suppress("UNCHECKED_CAST")
class FromValueResCalcViewModelFactory(private val iResistor: IResistor): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FromValueResCalcViewModel(iResistor) as T
    }
}