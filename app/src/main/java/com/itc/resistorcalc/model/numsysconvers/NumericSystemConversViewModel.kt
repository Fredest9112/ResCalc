package com.itc.resistorcalc.model.numsysconvers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.itc.resistorcalc.data.Constants.Companion.NOT_VALID_CONVERSION

class NumericSystemConversViewModel: ViewModel() {

    private val _numberInput = MutableLiveData<String>()
    private val numberInput: LiveData<String> = _numberInput

    val binNumber: LiveData<String> = Transformations.map(numberInput){
        setNumberToBin(it)
    }

    val octNumber: LiveData<String> = Transformations.map(numberInput){
        setNumberToOct(it)
    }

    val decNumber: LiveData<String> = Transformations.map(numberInput){
        setNumberToDec(it)
    }

    val hexNumber: LiveData<String> = Transformations.map(numberInput){
        setNumberToHex(it)
    }

    fun setNumInput(numInput: String){
        _numberInput.value = numInput
    }

    private fun setNumberToBin(numberInput: String): String{
        return try{
            numberInput.toULong().toString(radix = 2)
        } catch (exception: Exception){
            try{
                setNumberToBin(numberInput.toLong(radix = 16).toString())
            } catch (exception: Exception){
                NOT_VALID_CONVERSION
            }
        }
    }

    private fun setNumberToOct(numberInput: String): String{
        return try{
            numberInput.toULong().toString(radix = 8)
        } catch (exception: Exception){
            try{
                setNumberToOct(numberInput.toLong(radix = 16).toString())
            } catch (exception: Exception){
                NOT_VALID_CONVERSION
            }
        }
    }

    private fun setNumberToDec(numberInput: String): String{
        return try{
            numberInput.toULong().toString(radix = 10)
        } catch (exception: Exception){
            try{
                setNumberToDec(numberInput.toLong(radix = 16).toString())
            } catch (exception: Exception){
                NOT_VALID_CONVERSION
            }
        }
    }

    private fun setNumberToHex(numberInput: String): String{
        return try{
            numberInput.toULong().toString(radix = 16)
        } catch (exception: Exception){
            try{
                setNumberToHex(numberInput.toLong(radix = 16).toString())
            } catch (exception: Exception){
                NOT_VALID_CONVERSION
            }
        }
    }
}