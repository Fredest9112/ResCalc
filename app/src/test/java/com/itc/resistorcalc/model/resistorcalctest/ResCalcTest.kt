package com.itc.resistorcalc.model.resistorcalctest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.itc.resistorcalc.data.Constants.Companion.AMARILLO
import com.itc.resistorcalc.data.Constants.Companion.AZUL
import com.itc.resistorcalc.data.Constants.Companion.BLANCO
import com.itc.resistorcalc.data.Constants.Companion.CAFE
import com.itc.resistorcalc.data.Constants.Companion.DORADO
import com.itc.resistorcalc.data.Constants.Companion.FIVE_BANDS
import com.itc.resistorcalc.data.Constants.Companion.FOUR_BANDS
import com.itc.resistorcalc.data.Constants.Companion.GRIS
import com.itc.resistorcalc.data.Constants.Companion.NARANJA
import com.itc.resistorcalc.data.Constants.Companion.NOT_USABLE
import com.itc.resistorcalc.data.Constants.Companion.PLATEADO
import com.itc.resistorcalc.data.Constants.Companion.ROJO
import com.itc.resistorcalc.data.Constants.Companion.SIX_BANDS
import com.itc.resistorcalc.data.Constants.Companion.USABLE
import com.itc.resistorcalc.data.Constants.Companion.VERDE
import com.itc.resistorcalc.data.Constants.Companion.VIOLETA
import com.itc.resistorcalc.data.resistor.ProvideResistor
import com.itc.resistorcalc.model.resistorcalc.ResCalcViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ResCalcTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var resCalcViewModel: ResCalcViewModel
    private lateinit var resistor: ProvideResistor

    @Before
    fun setup(){
        resistor = ProvideResistor()
        resCalcViewModel = ResCalcViewModel(resistor)
        resCalcViewModel.setInitialState()
    }

    @Test
    fun getInitialResistor_firstInstance_returnsDefault(){
        //Given a resistor
        val resistor = resistor.provideResistor()
        //When viewmodel receives a resistor
        val resistor2 = resCalcViewModel.resistor.value
        //Then check if they are the same
        assertEquals(resistor.tolerance, resistor2?.tolerance)
    }

    @Test
    fun getAFourBandsResistor_withValues_returnsCorrectValue(){
        resCalcViewModel.apply {
            //Given a resistor
            setNoOfBands(FOUR_BANDS)
            setBand1(CAFE)
            setBand2(ROJO)
            setMultiplier(ROJO)
            setTolerance(DORADO)
            //When viewModel calculates a resistor
            setResultForColors()
            //Then check if result is correct
            assertEquals(1200.0, resistResult.value)
        }
    }

    @Test
    fun getAFiveBandsResistor_withValues_returnsCorrectValue(){
        resCalcViewModel.apply {
            //Given a resistor
            setNoOfBands(FIVE_BANDS)
            setBand1(VIOLETA)
            setBand2(AZUL)
            setBand3(NARANJA)
            setMultiplier(AMARILLO)
            setTolerance(PLATEADO)
            //When viewModel calculates a resistor
            setResultForColors()
            //Then check if result is correct
            assertEquals(7630000.0, resistResult.value)
        }
    }

    @Test
    fun getASixBandsResistor_withValues_returnsCorrectValue(){
        resCalcViewModel.apply {
            //Given a resistor
            setNoOfBands(SIX_BANDS)
            setBand1(VERDE)
            setBand2(GRIS)
            setBand3(BLANCO)
            setMultiplier(AZUL)
            setTolerance(VIOLETA)
            setPPM(NARANJA)
            //When viewModel receives a resistor
            setResultForColors()
            //Then check if result is correct
            assertEquals(589000000.0, resistResult.value)
        }
    }

    @Test
    fun getExpValue_compareTheoreticalValue_returnUsable(){
        resCalcViewModel.apply {
            //Given an experimental value
            val expResistorValue = 1234F
            setExpResult(expResistorValue)
            //When it has a Theoretical value
            setNoOfBands(FOUR_BANDS)
            setBand1(CAFE)
            setBand2(ROJO)
            setMultiplier(ROJO)
            setTolerance(DORADO)
            setResultForColors()
            //Check if it is usable
            setMinVal()
            setMaxVal()
            setState()
            assertEquals(USABLE, state.value)
        }
    }

    @Test
    fun getExpValue_compareTheoreticalValue_returnNotUsable(){
        resCalcViewModel.apply {
            //Given an experimental value
            val expResistorValue = 1345F
            setExpResult(expResistorValue)
            //When it has a Theoretical value
            setNoOfBands(FOUR_BANDS)
            setBand1(CAFE)
            setBand2(ROJO)
            setMultiplier(ROJO)
            setTolerance(DORADO)
            setResultForColors()
            //Check if it is usable
            setMinVal()
            setMaxVal()
            setState()
            assertEquals(NOT_USABLE, state.value)
        }
    }
}