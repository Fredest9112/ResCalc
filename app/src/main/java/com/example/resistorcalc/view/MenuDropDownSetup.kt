package com.example.resistorcalc.view

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.example.resistorcalc.R

object MenuDropDownSetup {

    fun setDropDownMenu(
        context: Context,
        autoCompleteTextView: AutoCompleteTextView,
        stringArray: Array<String>
    ){
        val arrayAdapter = ArrayAdapter(context, R.layout.list_items, stringArray)
        autoCompleteTextView.setAdapter(arrayAdapter)
    }
}