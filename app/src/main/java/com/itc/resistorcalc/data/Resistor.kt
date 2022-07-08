package com.itc.resistorcalc.data

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class Resistor(
    band1: Double?, band2: Double?, band3: Double?, multiplier: Double?,
    tolerance: Double?, ppm: Double?
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