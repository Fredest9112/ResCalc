package com.itc.resistorcalc.di

import com.itc.resistorcalc.view.fromvalueresistor.FromValueResistorFragment
import com.itc.resistorcalc.view.resistorcalc.ResistorCalcFragment
import com.itc.resistorcalc.view.resistorcalc.ResistorDetailsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ResistorModule::class])
interface AppComponent {

    fun injectResistorCalcFragment(fragment: ResistorCalcFragment)
    fun injectResistorCalcFragment(fragment: ResistorDetailsFragment)
    fun injectFromValueResistorFragment(fragment: FromValueResistorFragment)
}