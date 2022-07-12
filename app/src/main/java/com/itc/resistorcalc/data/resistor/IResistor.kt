package com.itc.resistorcalc.data.resistor

interface IResistor {

        var band1: Double?

        var band2: Double?

        var band3: Double?

        var multiplier: Double?

        var tolerance: Double?

        var ppm: Double?

        fun getResistor(): Resistor
}