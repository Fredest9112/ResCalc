package com.itc.resistorcalc

import android.app.Application
import com.itc.resistorcalc.di.AppComponent
import com.itc.resistorcalc.di.DaggerAppComponent

open class MyApp : Application() {

    open val appComponent: AppComponent by lazy {
        DaggerAppComponent.create()
    }
}