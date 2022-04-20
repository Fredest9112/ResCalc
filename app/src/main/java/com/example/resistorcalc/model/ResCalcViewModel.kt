package com.example.resistorcalc.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.resistorcalc.data.ResistorValues
import com.example.resistorcalc.model.Constants.Companion.FIVE_BANDS
import com.example.resistorcalc.model.Constants.Companion.FOUR_BANDS
import com.example.resistorcalc.model.Constants.Companion.NOT_USABLE
import com.example.resistorcalc.model.Constants.Companion.SIX_BANDS
import com.example.resistorcalc.model.Constants.Companion.USABLE
import com.example.resistorcalc.model.Constants.Companion.ZERO
import java.text.DecimalFormat

class ResCalcViewModel : ViewModel() {

    private val _noOfBands = MutableLiveData<Int>()
    val noOfBands: LiveData<Int> = _noOfBands

    private val _band1 = MutableLiveData<Double?>()
    val band1: LiveData<String?> = Transformations.map(_band1) {
        ResistorValues.valuesToBands[it?.toInt().toString()]
    }

    private val _band2 = MutableLiveData<Double?>()
    val band2: LiveData<String?> = Transformations.map(_band2) {
        ResistorValues.valuesToBands[it?.toInt().toString()]
    }

    private val _band3 = MutableLiveData<Double?>()
    val band3: LiveData<String?> = Transformations.map(_band3) {
        ResistorValues.valuesToBands[it?.toInt().toString()]
    }

    private val _multiplier = MutableLiveData<Double>()
    val multiplier: LiveData<String?> = Transformations.map(_multiplier) {
        ResistorValues.valuesToMultiplierBand[it]
    }

    private val _tolerance = MutableLiveData<Double>()
    val tolerance: LiveData<String?> = Transformations.map(_tolerance) { it.toString() }
    val sTolerance: LiveData<String?> = Transformations.map(_tolerance) {
        ResistorValues.valuesToTolerance[it]
    }

    private val _ppm = MutableLiveData<Double>()
    val ppm: LiveData<Double> = _ppm
    val sPPM: LiveData<String?> = Transformations.map(_ppm) {
        ResistorValues.valuesToPPM[it]
    }

    private val _resistResult = MutableLiveData<Double>()
    val resistResult: LiveData<String> = Transformations.map(_resistResult) {
        DecimalFormat.getInstance().format(it)
    }

    private val _expValue = MutableLiveData<Double>()
    val expValue: LiveData<String> = Transformations.map(_expValue) {
        DecimalFormat.getInstance().format(it)
    }

    private val _maxVal = MutableLiveData<Double>()
    val maxVal: LiveData<String> = Transformations.map(_maxVal) {
        DecimalFormat.getInstance().format(it)
    }

    private val _minVal = MutableLiveData<Double>()
    val minVal: LiveData<String> = Transformations.map(_minVal) {
        DecimalFormat.getInstance().format(it)
    }

    private val _state = MutableLiveData<String>()
    val state: LiveData<String> = _state

    private val _isDetailsValid = MutableLiveData<Boolean>()
    val isDetailsValid: LiveData<Boolean> = _isDetailsValid

    fun setInitialState() {
        _noOfBands.value = FOUR_BANDS
        _resistResult.value = ZERO
        _tolerance.value = ZERO
        _multiplier.value = ZERO
        _band1.value = null
        _band2.value = null
        _band3.value = null
    }

    fun setNoOfBands(bands: Int) {
        _noOfBands.value = bands
    }

    fun setBand1(band1: String) {
        _band1.value = ResistorValues.bandValues[band1]
    }

    fun setBand2(band2: String) {
        _band2.value = ResistorValues.bandValues[band2]
    }

    fun setBand3(band3: String) {
        _band3.value = ResistorValues.bandValues[band3]
    }

    fun setMultiplier(multiplier: String) {
        _multiplier.value = ResistorValues.multiplierValues[multiplier]
    }

    fun setTolerance(tolerance: String) {
        if (ResistorValues.toleranceValues[tolerance] == null) {
            _tolerance.value = ZERO
        } else {
            _tolerance.value = ResistorValues.toleranceValues[tolerance]
        }
    }

    fun setPPM(ppm: String) {
        _ppm.value = ResistorValues.ppmValues[ppm]
    }

    fun setResultForColors() {
        when (noOfBands.value) {
            FOUR_BANDS -> {
                _resistResult.value = getFourBandsResultForColors(
                    _band1.value,
                    _band2.value, _multiplier.value, _tolerance.value
                )
            }
            FIVE_BANDS -> {
                _resistResult.value = getFiveBandsResultForColors(
                    _band1.value,
                    _band2.value, _band3.value, _multiplier.value, _tolerance.value
                )
            }
            SIX_BANDS -> {
                _resistResult.value = getSixBandsResultForColors(
                    _band1.value,
                    _band2.value, _band3.value, _multiplier.value, _tolerance.value, _ppm.value
                )
            }
        }
    }

    fun setExpResult(expValue: Float) {
        _expValue.value = expValue.toDouble()
    }

    fun setMaxVal() {
        _maxVal.value =
            _resistResult.value?.plus((_tolerance.value?.div(100)!! * _resistResult.value!!))
    }

    fun setMinVal() {
        _minVal.value =
            _resistResult.value?.minus((_tolerance.value?.div(100)!! * _resistResult.value!!))
    }

    fun setState() {
        if (_expValue.value!! >= _minVal.value!! && _expValue.value!! <= _maxVal.value!!) {
            _state.value = USABLE
        } else {
            _state.value = NOT_USABLE
        }
    }

    private fun getFourBandsResultForColors(
        band1: Double?, band2: Double?,
        multiplier: Double?, tolerance: Double?
    ): Double {
        return if (band1 != null && band2 != null && multiplier != null &&
            tolerance != null && tolerance != ZERO
        ) {
            ((band1 * 10) + band2) * multiplier
        } else {
            ZERO
        }
    }

    private fun getFiveBandsResultForColors(
        band1: Double?,
        band2: Double?,
        band3: Double?,
        multiplier: Double?,
        tolerance: Double?
    ): Double {
        return if (band1 != null && band2 != null && band3 != null && multiplier != null &&
            tolerance != null && tolerance != ZERO
        ) {
            ((band1 * 100) + (band2 * 10) + band3) * multiplier
        } else {
            ZERO
        }
    }

    private fun getSixBandsResultForColors(
        band1: Double?,
        band2: Double?,
        band3: Double?,
        multiplier: Double?,
        tolerance: Double?,
        ppm: Double?
    ): Double {
        return if (band1 != null && band2 != null && band3 != null &&
            multiplier != null && tolerance != null && tolerance != ZERO && ppm != null
        ) {
            ((band1 * 100) + (band2 * 10) + band3) * multiplier
        } else {
            ZERO
        }
    }

    fun setBntDetailsValidator() {
        when (noOfBands.value) {
            SIX_BANDS -> {
                _isDetailsValid.value =
                    _resistResult.value != ZERO && _tolerance.value != ZERO && _ppm.value != null
            }
            else -> {
                _isDetailsValid.value = _resistResult.value != ZERO && _tolerance.value != ZERO
            }
        }
    }

    //methods for FromValueResistorFragment

    fun setResultForValues(resistInput: Long) {
        val input = resistInput.toString()
        if (noOfBands.value == FOUR_BANDS) {
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