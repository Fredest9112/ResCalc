package com.itc.resistorcalc.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.itc.resistorcalc.data.PropertyAwareMutableLiveData
import com.itc.resistorcalc.data.Resistor
import com.itc.resistorcalc.data.ResistorValues

class FromValueResCalcViewModel : ViewModel() {

    private val _resistor = PropertyAwareMutableLiveData<Resistor>()
    private val resistor: LiveData<Resistor> = _resistor

    private val _noOfBands = MutableLiveData<NoOfBands>()
    val noOfBands: LiveData<NoOfBands> = _noOfBands

    val stringBand1: LiveData<String?> = Transformations.map(resistor) {
        ResistorValues.valuesToBands[it.band1]
    }

    val stringBand2: LiveData<String?> = Transformations.map(resistor) {
        ResistorValues.valuesToBands[it.band2]
    }

    val stringBand3: LiveData<String?> = Transformations.map(resistor) {
        ResistorValues.valuesToBands[it.band3]
    }

    val stringMultiplier: LiveData<String?> = Transformations.map(resistor) {
        ResistorValues.valuesToMultiplierBand[it.multiplier]
    }

    val tolerance: LiveData<String?> = Transformations.map(resistor) { it.tolerance.toString() }
    val stringTolerance: LiveData<String?> = Transformations.map(resistor) {
        ResistorValues.valuesToTolerance[it.tolerance]
    }

    val stringPPM: LiveData<String?> = Transformations.map(resistor) {
        ResistorValues.valuesToPPM[it.ppm]
    }

    init {
        _resistor.value = Resistor(null, null, null, null, null, null)
        _noOfBands.value = NoOfBands.FOUR_BANDS
    }

    fun setTolerance(tolerance: String) {
        when {
            tolerance.isEmpty() -> {
                _resistor.value?.tolerance = Constants.ZERO
            }
            else -> {
                _resistor.value?.tolerance = ResistorValues.toleranceValues[tolerance]
            }
        }
    }

    fun setPPM(ppm: String) {
        when {
            ppm.isEmpty() -> {
                _resistor.value?.ppm = Constants.ZERO
            }
            else -> {
                _resistor.value?.ppm = ResistorValues.ppmValues[ppm]
            }
        }
    }

    fun setNoOfBands(noOfBands: Int) {
        when (noOfBands) {
            4 -> {
                _noOfBands.value = NoOfBands.FOUR_BANDS
            }
            5 -> {
                _noOfBands.value = NoOfBands.FIVE_BANDS
            }
            6 -> {
                _noOfBands.value = NoOfBands.SIX_BANDS
            }
        }
    }

    fun setResultForValues(resistInput: Long) {
        val input = resistInput.toString()
        if (noOfBands.value == NoOfBands.FOUR_BANDS) {
            _resistor.value?.band1 = input[0].toString().toDouble()
            _resistor.value?.band2 = input[1].toString().toDouble()
            var numb = resistInput
            var mult = 1.0
            while (numb > 99) {
                mult *= 10
                numb = numb.div(10)
            }
            _resistor.value?.multiplier = mult
        } else {
            _resistor.value?.band1 = input[0].toString().toDouble()
            _resistor.value?.band2 = input[1].toString().toDouble()
            _resistor.value?.band3 = input[2].toString().toDouble()
            var numb = resistInput
            var mult = 1.0
            while (numb > 999) {
                mult *= 10
                numb = numb.div(10)
            }
            _resistor.value?.multiplier = mult
        }
    }
}