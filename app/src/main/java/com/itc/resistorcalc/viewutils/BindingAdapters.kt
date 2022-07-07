package com.itc.resistorcalc.viewutils

import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.view.get
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.itc.resistorcalc.data.Resistor
import com.itc.resistorcalc.model.NoOfBands
import com.itc.resistorcalc.model.ResCalcViewModel
import com.itc.resistorcalc.view.ColorCardView

@BindingAdapter("setBand3State")
fun bindBand3InputLayoutVisibility(textInputLayout: TextInputLayout, noOfBands: NoOfBands){
    when(noOfBands){
        NoOfBands.FOUR_BANDS -> {
            textInputLayout.visibility = View.GONE
        }
        else -> {
            textInputLayout.visibility = View.VISIBLE
        }
    }
}

@BindingAdapter("setBand3CardViewState")
fun bindBand3CardViewVisibility(cardView: CardView, noOfBands: NoOfBands){
    when(noOfBands){
        NoOfBands.FOUR_BANDS -> {
            cardView.visibility = View.GONE
        }
        else -> {
            cardView.visibility = View.VISIBLE
        }
    }
}

@BindingAdapter("setPPMState")
fun bindPPMInputLayoutVisibility(textInputLayout: TextInputLayout, noOfBands: NoOfBands){
    when(noOfBands){
        NoOfBands.FOUR_BANDS -> {
            textInputLayout.visibility = View.GONE
        }
        NoOfBands.FIVE_BANDS -> {
            textInputLayout.visibility = View.GONE
        }
        else -> {
            textInputLayout.visibility = View.VISIBLE
        }
    }
}

@BindingAdapter("setPPMCardViewState")
fun bindPPMCardViewVisibility(cardView: CardView, noOfBands: NoOfBands){
    when(noOfBands){
        NoOfBands.FOUR_BANDS -> {
            cardView.visibility = View.GONE
        }
        NoOfBands.FIVE_BANDS -> {
            cardView.visibility = View.GONE
        }
        else -> {
            cardView.visibility = View.VISIBLE
        }
    }
}

@BindingAdapter("setColorForBandIndicator")
fun bindColorForBandIndicator(color1Indicator: CardView, color: String){
    ColorCardView.setCardViewColor(
        color, color1Indicator.context, color1Indicator
    )
}