package com.example.resistorcalc.view

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.example.resistorcalc.R

object MenuDropDownSetup {

    fun setDropDownMenu(
        context: Context?,
        textView: AutoCompleteTextView,
        stringArray: Array<String>
    ) {
        val arrayAdapter = ArrayAdapter(context!!, R.layout.list_items, stringArray)
        textView.setAdapter(arrayAdapter)
        textView.showDropDown(arrayAdapter)
    }

    private fun AutoCompleteTextView.showDropDown(adapter: ArrayAdapter<String>?){
        if(!TextUtils.isEmpty(this.text.toString())){
            adapter?.filter?.filter(null)
        }
    }
}