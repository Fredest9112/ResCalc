package com.itc.resistorcalc.model.fromvalueresistortest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.itc.resistorcalc.data.NoOfBands
import com.itc.resistorcalc.data.resistor.ProvideResistor
import com.itc.resistorcalc.model.fromvalueresistor.FromValueResCalcViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FromValueResCalcTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var fromValueResCalcViewModel: FromValueResCalcViewModel
    private lateinit var resistor: ProvideResistor

    @Before
    fun setup(){
        resistor = ProvideResistor()
        fromValueResCalcViewModel = FromValueResCalcViewModel(resistor)
    }

    @Test
    fun getInitialResistor_firstInstance_returnsDefault(){
        //Given a resistor

        val resistor = resistor.provideResistor()
        //When viewmodel receives a resistor
        val resistor2 = fromValueResCalcViewModel.resistor.value
        //Then check if they are the same
        assertEquals(resistor.tolerance, resistor2?.tolerance)
    }

    @Test
    fun fromFourBandsValue_calculate_returnsResistor(){
        //Given a value
        val resistInput = 1200L
        //When noOfBands is 4
        fromValueResCalcViewModel.setNoOfBands(4)
        fromValueResCalcViewModel.setResultForValues(resistInput)
        //Then check if resistor was correctly calculated
        assertEquals(1.0, fromValueResCalcViewModel.resistor.value?.band1)
        assertEquals(2.0, fromValueResCalcViewModel.resistor.value?.band2)
        assertEquals(100.0, fromValueResCalcViewModel.resistor.value?.multiplier)
    }

    @Test
    fun fromFiveBandsValue_calculate_returnsResistor(){
        //Given a value
        val resistInput = 7630000L
        //When noOfBands is 5
        fromValueResCalcViewModel.setNoOfBands(5)
        fromValueResCalcViewModel.setResultForValues(resistInput)
        //Then check if resistor was correctly calculated
        assertEquals(7.0, fromValueResCalcViewModel.resistor.value?.band1)
        assertEquals(6.0, fromValueResCalcViewModel.resistor.value?.band2)
        assertEquals(3.0, fromValueResCalcViewModel.resistor.value?.band3)
        assertEquals(10000.0, fromValueResCalcViewModel.resistor.value?.multiplier)
    }

    @Test
    fun fromSixBandsValue_calculate_returnsResistor(){
        //Given a value
        val resistInput = 589000000L
        //When noOfBands is 5
        fromValueResCalcViewModel.setNoOfBands(6)
        fromValueResCalcViewModel.setResultForValues(resistInput)
        //Then check if resistor was correctly calculated
        assertEquals(5.0, fromValueResCalcViewModel.resistor.value?.band1)
        assertEquals(8.0, fromValueResCalcViewModel.resistor.value?.band2)
        assertEquals(9.0, fromValueResCalcViewModel.resistor.value?.band3)
        assertEquals(1000000.0, fromValueResCalcViewModel.resistor.value?.multiplier)
    }
}