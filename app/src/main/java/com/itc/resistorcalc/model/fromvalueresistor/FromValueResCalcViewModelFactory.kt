package com.itc.resistorcalc.model.fromvalueresistor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.itc.resistorcalc.data.resistor.IResistor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Suppress("UNCHECKED_CAST")
class FromValueResCalcViewModelFactory @Inject constructor(private val iResistor: IResistor) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FromValueResCalcViewModel(iResistor) as T
    }
}