package com.itc.resistorcalc.di

import com.itc.resistorcalc.data.resistor.IResistor
import com.itc.resistorcalc.data.resistor.ProvideResistor
import dagger.Binds
import dagger.Module

@Module
abstract class ResistorModule {
    @Binds
    abstract fun provideResistor(provideResistor: ProvideResistor): IResistor
}