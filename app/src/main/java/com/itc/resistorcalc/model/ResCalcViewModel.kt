package com.itc.resistorcalc.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.itc.resistorcalc.data.Resistor
import com.itc.resistorcalc.data.ResistorValues
import com.itc.resistorcalc.model.Constants.Companion.NOT_USABLE
import com.itc.resistorcalc.model.Constants.Companion.USABLE
import com.itc.resistorcalc.model.Constants.Companion.ZERO
import java.text.DecimalFormat

enum class NoOfBands{
    FOUR_BANDS, FIVE_BANDS, SIX_BANDS
}

class ResCalcViewModel : ViewModel() {

    private val _resistor = MutableLiveData<Resistor>()
    val resistor: LiveData<Resistor> = _resistor

    private val _noOfBands = MutableLiveData<NoOfBands>()
    val noOfBands: LiveData<NoOfBands> = _noOfBands

    private val _band1 = MutableLiveData<Double?>()
    val stringBand1: LiveData<String?> = Transformations.map(resistor) {
        ResistorValues.valuesToBands[it.band1]
    }

    private val _band2 = MutableLiveData<Double?>()
    val stringBand2: LiveData<String?> = Transformations.map(resistor) {
        ResistorValues.valuesToBands[it.band2]
    }

    private val _band3 = MutableLiveData<Double?>()
    val stringBand3: LiveData<String?> = Transformations.map(resistor) {
        ResistorValues.valuesToBands[it.band3]
    }

    private val _multiplier = MutableLiveData<Double>()
    val stringMultiplier: LiveData<String?> = Transformations.map(resistor) {
        ResistorValues.valuesToMultiplierBand[it.multiplier]
    }

    private val _tolerance = MutableLiveData<Double>()
    val tolerance: LiveData<String?> = Transformations.map(_tolerance) { it.toString() }
    val stringTolerance: LiveData<String?> = Transformations.map(resistor) {
        ResistorValues.valuesToTolerance[it.tolerance]
    }

    private val _ppm = MutableLiveData<Double>()
    val ppm: LiveData<Double> = _ppm
    val stringPPM: LiveData<String?> = Transformations.map(resistor) {
        ResistorValues.valuesToPPM[it.ppm]
    }

    private val _resistResult = MutableLiveData<Double>()
    private val resistResult: LiveData<Double> = _resistResult
    val stringResistResult: LiveData<String> = Transformations.map(_resistResult) {
        DecimalFormat.getInstance().format(it)
    }

    private val _expValue = MutableLiveData<Double>()
    private val expValue: LiveData<Double> = _expValue
    val stringExpValue: LiveData<String> = Transformations.map(_expValue) {
        DecimalFormat.getInstance().format(it)
    }

    private val _maxVal = MutableLiveData<Double>()
    private val maxVal: LiveData<Double> = _maxVal
    val stringMaxVal: LiveData<String> = Transformations.map(_maxVal) {
        DecimalFormat.getInstance().format(it)
    }

    private val _minVal = MutableLiveData<Double>()
    private val minVal: LiveData<Double> = _minVal
    val stringMinVal: LiveData<String> = Transformations.map(_minVal) {
        DecimalFormat.getInstance().format(it)
    }

    private val _state = MutableLiveData<String>()
    val state: LiveData<String> = _state

    private val _areDetailsValid = MutableLiveData<Boolean>()
    val areDetailsValid: LiveData<Boolean> = _areDetailsValid

    fun setInitialState() {
        _resistor.value = Resistor(null, null, null, null, null,null)
        _noOfBands.value = NoOfBands.FOUR_BANDS
    }

    fun setNoOfBands(noOfBands: Int) {
        when(noOfBands){
            4 -> {_noOfBands.value = NoOfBands.FOUR_BANDS}
            5 -> {_noOfBands.value = NoOfBands.FIVE_BANDS}
            6 -> {_noOfBands.value = NoOfBands.SIX_BANDS}
        }

    }

    fun setBand1(band1: String) {
        _resistor.value?.band1 = ResistorValues.bandValues[band1]
    }

    fun setBand2(band2: String) {
        _resistor.value?.band2 = ResistorValues.bandValues[band2]
    }

    fun setBand3(band3: String) {
        _resistor.value?.band3 = ResistorValues.bandValues[band3]
    }

    fun setMultiplier(multiplier: String) {
        _resistor.value?.multiplier = ResistorValues.multiplierValues[multiplier]
    }

    fun setTolerance(tolerance: String) {
        _resistor.value?.tolerance = ResistorValues.toleranceValues[tolerance]
    }

    fun setPPM(ppm: String) {
        _resistor.value?.ppm = ResistorValues.ppmValues[ppm]
    }

    fun setResultForColors() {
        _resistResult.value = resistor.value?.let { getBandsResultForColors(it) }
    }

    private fun getBandsResultForColors(
        resistor: Resistor
    ): Double {
        when(noOfBands.value){
            NoOfBands.FOUR_BANDS -> {
                return if (resistor.band1 != null && resistor.band2 != null && resistor.multiplier != null &&
                    resistor.tolerance != null
                ) {
                    ((resistor.band1!! * 10) + resistor.band2!!) * resistor.multiplier!!
                } else {
                    ZERO
                }
            }
            NoOfBands.FIVE_BANDS -> {
                return if (resistor.band1 != null && resistor.band2 != null && resistor.band3 != null
                    && resistor.multiplier != null && resistor.tolerance != null
                ) {
                    ((resistor.band1!! * 100) + (resistor.band2!! * 10) + resistor.band3!!) *
                            resistor.multiplier!!
                } else {
                    ZERO
                }
            }
            else -> {
                return if (resistor.band1 != null && resistor.band2 != null && resistor.band3 != null &&
                    resistor.multiplier != null && resistor.tolerance != null && resistor.ppm != null
                ) {
                    ((resistor.band1!! * 100) + (resistor.band2!! * 10) + resistor.band3!!) *
                            resistor.multiplier!!
                } else {
                    ZERO
                }
            }
        }
    }

    fun setBntDetailsValidator() {
        when (noOfBands.value) {
            NoOfBands.SIX_BANDS -> {
                _areDetailsValid.value =
                    resistResult.value != null && resistResult.value != ZERO
                            && resistor.value?.tolerance != null && resistor.value?.ppm != null
            }
            else -> {
                _areDetailsValid.value = resistResult.value != null && resistResult.value != ZERO &&
                        resistor.value?.tolerance != null
            }
        }
    }

    fun setExpResult(expValue: Float) {
        _expValue.value = expValue.toDouble()
    }

    fun setMaxVal() {
        _maxVal.value = resistResult.value?.plus((resistor.value?.tolerance?.div(100)!!
                * resistResult.value!!))
    }

    fun setMinVal() {
        _minVal.value =
            resistResult.value?.minus((resistor.value?.tolerance?.div(100)!!
                    * resistResult.value!!))
    }

    fun setState() {
        if (expValue.value!! >= minVal.value!! && expValue.value!! <= maxVal.value!!) {
            _state.value = USABLE
        } else {
            _state.value = NOT_USABLE
        }
    }

    //methods for FromValueResistorFragment

    fun setResultForValues(resistInput: Long) {
        val input = resistInput.toString()
        if (noOfBands.value == NoOfBands.FOUR_BANDS) {
            _band1.value = input[0].toString().toDouble()
            _band2.value = input[1].toString().toDouble()
            var numb = resistInput
            var mult = 1.0
            while (numb > 99) {
                mult *= 10
                numb = numb.div(10)
            }
            _multiplier.value = mult
        } else {
            _band1.value = input[0].toString().toDouble()
            _band2.value = input[1].toString().toDouble()
            _band3.value = input[2].toString().toDouble()
            var numb = resistInput
            var mult = 1.0
            while (numb > 999) {
                mult *= 10
                numb = numb.div(10)
            }
            _multiplier.value = mult
        }
    }
}