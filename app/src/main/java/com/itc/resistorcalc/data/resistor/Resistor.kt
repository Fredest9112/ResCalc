package com.itc.resistorcalc.data.resistor

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class Resistor(
    band1: Double? = null, band2: Double? = null, band3: Double? = null,
    multiplier: Double? = null, tolerance: Double? = null, ppm: Double? = null
) : BaseObservable(), IResistor {

    @Bindable
    override var band1: Double? = band1
        set(value) {
            field = value
            notifyChange()
        }

    @Bindable
    override var band2: Double? = band2
        set(value) {
            field = value
            notifyChange()
        }

    @Bindable
    override var band3: Double? = band3
        set(value) {
            field = value
            notifyChange()
        }

    @Bindable
    override var multiplier: Double? = multiplier
        set(value) {
            field = value
            notifyChange()
        }

    @Bindable
    override var tolerance: Double? = tolerance
        set(value) {
            field = value
            notifyChange()
        }

    @Bindable
    override var ppm: Double? = ppm
        set(value) {
            field = value
            notifyChange()
        }

    override fun getResistor(): Resistor{
        return Resistor(band1, band2, band3, tolerance, multiplier, ppm)
    }
}