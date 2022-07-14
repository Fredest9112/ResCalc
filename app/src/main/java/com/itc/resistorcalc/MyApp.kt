package com.itc.resistorcalc

import android.app.Application
import com.itc.resistorcalc.di.AppComponent
import com.itc.resistorcalc.di.DaggerAppComponent

class MyApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.create()
    }
}