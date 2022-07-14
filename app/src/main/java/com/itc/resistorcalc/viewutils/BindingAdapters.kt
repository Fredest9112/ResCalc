package com.itc.resistorcalc.viewutils

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.itc.resistorcalc.data.NoOfBands

@BindingAdapter("setBand3State")
fun bindBand3InputLayoutVisibility(textInputLayout: TextInputLayout, noOfBands: NoOfBands?) {
    when (noOfBands) {
        NoOfBands.FOUR_BANDS -> {
            textInputLayout.visibility = View.GONE
        }
        else -> {
            textInputLayout.visibility = View.VISIBLE
        }
    }
}

@BindingAdapter("setBand3CardViewState")
fun bindBand3CardViewVisibility(cardView: CardView, noOfBands: NoOfBands?) {
    when (noOfBands) {
        NoOfBands.FOUR_BANDS -> {
            cardView.visibility = View.GONE
        }
        else -> {
            cardView.visibility = View.VISIBLE
        }
    }
}

@BindingAdapter("setPPMState")
fun bindPPMInputLayoutVisibility(textInputLayout: TextInputLayout, noOfBands: NoOfBands?) {
    when (noOfBands) {
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
fun bindPPMCardViewVisibility(cardView: CardView, noOfBands: NoOfBands?) {
    when (noOfBands) {
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

@BindingAdapter("setPPMTitle")
fun bindPPMTitleVisibility(textView: TextView, noOfBands: NoOfBands?) {
    when (noOfBands) {
        NoOfBands.FOUR_BANDS -> {
            textView.visibility = View.GONE
        }
        NoOfBands.FIVE_BANDS -> {
            textView.visibility = View.GONE
        }
        else -> {
            textView.visibility = View.VISIBLE
        }
    }
}

@BindingAdapter("setBand3Title")
fun bindBand3TitleVisibility(textView: TextView, noOfBands: NoOfBands?) {
    when (noOfBands) {
        NoOfBands.FOUR_BANDS -> {
            textView.visibility = View.GONE
        }
        else -> {
            textView.visibility = View.VISIBLE
        }
    }
}

@BindingAdapter("setColorForBandIndicator")
fun bindColorForBandIndicator(colorIndicator: CardView, color: String?) {
    ColorCardView.setResCalcCardViewColor(
        color, colorIndicator.context, colorIndicator
    )
}