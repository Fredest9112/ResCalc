package com.itc.resistorcalc.model.resistorcalc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.itc.resistorcalc.data.PropertyAwareMutableLiveData
import com.itc.resistorcalc.data.ResistorValues
import com.itc.resistorcalc.data.Constants.Companion.NOT_USABLE
import com.itc.resistorcalc.data.Constants.Companion.USABLE
import com.itc.resistorcalc.data.Constants.Companion.ZERO
import com.itc.resistorcalc.data.NoOfBands
import com.itc.resistorcalc.data.resistor.IResistor
import com.itc.resistorcalc.data.resistor.Resistor
import java.text.DecimalFormat

class ResCalcViewModel(iResistor: IResistor) : ViewModel() {

    private val _resistor = PropertyAwareMutableLiveData<Resistor>()
    val resistor: LiveData<Resistor> = _resistor

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

    val ppm: LiveData<String?> = Transformations.map(resistor) { it.ppm.toString() }
    val stringPPM: LiveData<String?> = Transformations.map(resistor) {
        ResistorValues.valuesToPPM[it.ppm]
    }

    private val _resistResult = MutableLiveData<Double>()
    val resistResult: LiveData<Double> = _resistResult
    val stringResistResult: LiveData<String> = Transformations.map(resistResult) {
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

    init {
        _resistor.value = iResistor.provideResistor()
        _noOfBands.value = NoOfBands.FOUR_BANDS
        _resistResult.value = ZERO
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
        when {
            tolerance.isEmpty() -> {
                _resistor.value?.tolerance = ZERO
            }
            else -> {
                _resistor.value?.tolerance = ResistorValues.toleranceValues[tolerance]
            }
        }
    }

    fun setPPM(ppm: String) {
        when {
            ppm.isEmpty() -> {
                _resistor.value?.ppm = ZERO
            }
            else -> {
                _resistor.value?.ppm = ResistorValues.ppmValues[ppm]
            }
        }
    }

    fun setResultForColors() {
        val resistor = resistor.value
        _resistResult.value =
            when (noOfBands.value) {
                NoOfBands.FOUR_BANDS -> {
                    if (resistor?.band1 != null && resistor.band2 != null && resistor.multiplier != null &&
                        resistor.tolerance != ZERO
                    ) {
                        ((resistor.band1!! * 10) + resistor.band2!!) * resistor.multiplier!!
                    } else {
                        ZERO
                    }
                }
                NoOfBands.FIVE_BANDS -> {
                    if (resistor?.band1 != null && resistor.band2 != null && resistor.band3 != null
                        && resistor.multiplier != null && resistor.tolerance != ZERO
                    ) {
                        ((resistor.band1!! * 100) + (resistor.band2!! * 10) + resistor.band3!!) *
                                resistor.multiplier!!
                    } else {
                        ZERO
                    }
                }
                else -> {
                    if (resistor?.band1 != null && resistor.band2 != null && resistor.band3 != null &&
                        resistor.multiplier != null && resistor.tolerance != ZERO && resistor.ppm != ZERO
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
                _areDetailsValid.value = resistResult.value != null && resistResult.value != ZERO
                        && resistor.value?.tolerance != null && resistor.value?.ppm != null
                        && resistor.value?.tolerance != ZERO && resistor.value?.ppm != ZERO
            }
            else -> {
                _areDetailsValid.value = resistResult.value != null && resistResult.value != ZERO &&
                        resistor.value?.tolerance != null && resistor.value?.tolerance != ZERO
            }
        }
    }

    fun setExpResult(expValue: Float) {
        _expValue.value = expValue.toDouble()
    }

    fun setMaxVal() {
        _maxVal.value = resistResult.value?.plus(
            (resistor.value?.tolerance?.div(100)!!
                    * resistResult.value!!)
        )
    }

    fun setMinVal() {
        _minVal.value =
            resistResult.value?.minus(
                (resistor.value?.tolerance?.div(100)!!
                        * resistResult.value!!)
            )
    }

    fun setState() {
        if (expValue.value!! >= minVal.value!! && expValue.value!! <= maxVal.value!!) {
            _state.value = USABLE
        } else {
            _state.value = NOT_USABLE
        }
    }
}