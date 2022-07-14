package com.itc.resistorcalc.data.resistor

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import javax.inject.Inject

class Resistor(
    band1: Double? = null, band2: Double? = null, band3: Double? = null,
    multiplier: Double? = null, tolerance: Double? = 0.0, ppm: Double? = 0.0
) : BaseObservable() {
    @Bindable
    var band1: Double? = band1
        set(value) {
            field = value
            notifyChange()
        }

    @Bindable
    var band2: Double? = band2
        set(value) {
            field = value
            notifyChange()
        }

    @Bindable
    var band3: Double? = band3
        set(value) {
            field = value
            notifyChange()
        }

    @Bindable
    var multiplier: Double? = multiplier
        set(value) {
            field = value
            notifyChange()
        }

    @Bindable
    var tolerance: Double? = tolerance
        set(value) {
            field = value
            notifyChange()
        }

    @Bindable
    var ppm: Double? = ppm
        set(value) {
            field = value
            notifyChange()
        }
}