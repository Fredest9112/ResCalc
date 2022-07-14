package com.itc.resistorcalc.model.resistorcalc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.itc.resistorcalc.data.resistor.IResistor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Suppress("UNCHECKED_CAST")
class ResCalcViewModelFactory @Inject constructor(private val iResistor: IResistor) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ResCalcViewModel(iResistor) as T
    }
}