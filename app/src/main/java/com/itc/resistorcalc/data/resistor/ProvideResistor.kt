package com.itc.resistorcalc.data.resistor

import javax.inject.Inject

class ProvideResistor @Inject constructor() : IResistor {

    override fun provideResistor(): Resistor {
        return Resistor()
    }
}