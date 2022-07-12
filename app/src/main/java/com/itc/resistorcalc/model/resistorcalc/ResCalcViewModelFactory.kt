package com.itc.resistorcalc.model.resistorcalc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.itc.resistorcalc.data.resistor.IResistor

@Suppress("UNCHECKED_CAST")
class ResCalcViewModelFactory(private val iResistor: IResistor?):
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return iResistor?.let { ResCalcViewModel(it) } as T
    }
}