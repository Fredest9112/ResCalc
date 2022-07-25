package com.itc.resistorcalc.model.resistorcalctest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.itc.resistorcalc.data.resistor.ProvideResistor
import com.itc.resistorcalc.data.resistor.Resistor
import com.itc.resistorcalc.model.resistorcalc.ResCalcViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.text.DecimalFormat

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
        //Given a resistor
        resCalcViewModel.setNoOfBands(4)
        resCalcViewModel.setBand1("Cafe")
        resCalcViewModel.setBand2("Rojo")
        resCalcViewModel.setMultiplier("Rojo")
        resCalcViewModel.setTolerance("Dorado")
        //When viewModel calculates a resistor
        resCalcViewModel.setResultForColors()
        //Then check if result is correct
        assertEquals(1200.0, resCalcViewModel.resistResult.value)
    }

    @Test
    fun getAFiveBandsResistor_withValues_returnsCorrectValue(){
        //Given a resistor
        resCalcViewModel.setNoOfBands(5)
        resCalcViewModel.setBand1("Violeta")
        resCalcViewModel.setBand2("Azul")
        resCalcViewModel.setBand3("Naranja")
        resCalcViewModel.setMultiplier("Amarillo")
        resCalcViewModel.setTolerance("Plateado")
        //When viewModel calculates a resistor
        resCalcViewModel.setResultForColors()
        //Then check if result is correct
        assertEquals(7630000.0, resCalcViewModel.resistResult.value)
    }

    @Test
    fun getASixBandsResistor_withValues_returnsCorrectValue(){
        //Given a resistor
        resCalcViewModel.setNoOfBands(6)
        resCalcViewModel.setBand1("Verde")
        resCalcViewModel.setBand2("Gris")
        resCalcViewModel.setBand3("Blanco")
        resCalcViewModel.setMultiplier("Azul")
        resCalcViewModel.setTolerance("Violeta")
        resCalcViewModel.setPPM("Naranja")
        //When viewModel receives a resistor
        resCalcViewModel.setResultForColors()
        //Then check if result is correct
        assertEquals(589000000.0, resCalcViewModel.resistResult.value)
    }

    @Test
    fun getExpValue_compareTheoreticalValue_returnsUsability(){
        //Given an experimental value
        val expResistorValue = 1234F
        resCalcViewModel.setExpResult(expResistorValue)
        //When it has a Theoretical value
        resCalcViewModel.setNoOfBands(4)
        resCalcViewModel.setBand1("Cafe")
        resCalcViewModel.setBand2("Rojo")
        resCalcViewModel.setMultiplier("Rojo")
        resCalcViewModel.setTolerance("Dorado")
        resCalcViewModel.setResultForColors()
        //Check if it is usable
        resCalcViewModel.setMinVal()
        resCalcViewModel.setMaxVal()
        resCalcViewModel.setState()
        assertEquals("Usable", resCalcViewModel.state.value)
    }
}