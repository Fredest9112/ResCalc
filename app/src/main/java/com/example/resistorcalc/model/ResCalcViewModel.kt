package com.example.resistorcalc.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.resistorcalc.data.ResistorValues
import com.example.resistorcalc.model.Constants.Companion.FIVE_BANDS
import com.example.resistorcalc.model.Constants.Companion.FOUR_BANDS
import com.example.resistorcalc.model.Constants.Companion.NOT_USABLE
import com.example.resistorcalc.model.Constants.Companion.SIX_BANDS
import com.example.resistorcalc.model.Constants.Companion.STRING_ZERO
import com.example.resistorcalc.model.Constants.Companion.USABLE

class ResCalcViewModel : ViewModel() {

    private val _noOfBands = MutableLiveData<Int>()
    val noOfBands : LiveData<Int> = _noOfBands

    private val _band1 = MutableLiveData<Double>()

    private val _band2 = MutableLiveData<Double>()

    private val _band3 = MutableLiveData<Double>()

    private val _multiplier = MutableLiveData<Double>()

    private val _tolerance = MutableLiveData<Double?>()
    val tolerance : LiveData<Double?> = _tolerance

    private val _ppm = MutableLiveData<Double>()

    private val _result = MutableLiveData<String>()
    val result : LiveData<String> = _result

    private val _expValue = MutableLiveData<Double>()
    val expValue : LiveData<Double> = _expValue

    private val _nomValue = MutableLiveData<Double>()
    val nomValue : LiveData<Double> = _nomValue

    private val _maxVal = MutableLiveData<Double>()
    val maxVal : LiveData<Double> = _maxVal

    private val _minVal = MutableLiveData<Double>()
    val minVal : LiveData<Double> = _minVal

    private val _state = MutableLiveData<String>()
    val state : LiveData<String> = _state

    init{
        _noOfBands.value = FOUR_BANDS
        _result.value = STRING_ZERO
    }

    fun setNoOfBands(bands:Int){
        _noOfBands.value = bands
    }

    fun setBand1(band1:String){
        _band1.value = ResistorValues.bandValues[band1]
    }

    fun setBand2(band2:String){
        _band2.value = ResistorValues.bandValues[band2]
    }

    fun setBand3(band3:String){
        _band3.value = ResistorValues.bandValues[band3]
    }

    fun setMultiplier(multiplier:String){
        _multiplier.value = ResistorValues.multiplierValues[multiplier]
    }

    fun setTolerance(tolerance:String){
        _tolerance.value = ResistorValues.toleranceValues[tolerance]
    }

    fun setPPM(ppm:String){
        _ppm.value = ResistorValues.ppmValues[ppm]
    }

    fun setResult(){
        when(noOfBands.value){
            FOUR_BANDS -> {
                _result.value = getFourBandsResult(_band1.value,
                    _band2.value, _multiplier.value, _tolerance.value)
            }
            FIVE_BANDS -> {
                _result.value = getFiveBandsResult(_band1.value,
                    _band2.value, _band3.value, _multiplier.value, _tolerance.value)
            }
            SIX_BANDS -> {
                _result.value = getSixBandsResult(_band1.value,
                    _band2.value, _band3.value, _multiplier.value, _tolerance.value, _ppm.value)
            }
        }
    }

    fun setExpResult(expValue:Float){
        _expValue.value = expValue.toDouble()
    }

    fun setMaxVal(){
        _maxVal.value = nomValue.value?.plus((tolerance.value?.div(100)!! * nomValue.value!!))
    }

    fun setMinVal(){
        _minVal.value = nomValue.value?.minus((tolerance.value?.div(100)!! * nomValue.value!!))
    }

    fun setState() {
        if(_expValue.value!! > _minVal.value!! && _expValue.value!! < _maxVal.value!!){
            _state.value = USABLE
        } else{
            _state.value = NOT_USABLE
        }
    }

    private fun getFourBandsResult(band1: Double?, band2: Double?,
                                   multiplier: Double?, tolerance: Double?): String {
        return if(band1!=null && band2!=null && multiplier!=null && tolerance!=null){
            _nomValue.value = ((band1*10)+band2)*multiplier
            "${((band1*10)+band2)*multiplier} ohms, $tolerance %"
        } else{
            STRING_ZERO
        }
    }

    private fun getFiveBandsResult(
        band1: Double?,
        band2: Double?,
        band3: Double?,
        multiplier: Double?,
        tolerance: Double?
    ): String {
        return if(band1!=null && band2!=null && band3!=null && multiplier!=null && tolerance!=null){
            _nomValue.value = ((band1*100)+(band2*10)+band3)*multiplier
            "${((band1*100)+(band2*10)+band3)*multiplier} ohms, $tolerance %"
        } else{
            STRING_ZERO
        }
    }

    private fun getSixBandsResult(
        band1: Double?,
        band2: Double?,
        band3: Double?,
        multiplier: Double?,
        tolerance: Double?,
        ppm: Double?): String {
        return if(band1!=null && band2!=null && band3!=null &&
            multiplier!=null && tolerance!=null && ppm!=null){
            _nomValue.value = ((band1*100)+(band2*10)+band3)*multiplier
            "${((band1*100)+(band2*10)+band3)*multiplier} ohms, $tolerance %, $ppm ppm"
        } else{
            STRING_ZERO
        }
    }
}