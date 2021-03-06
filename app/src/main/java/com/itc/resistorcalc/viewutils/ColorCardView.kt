package com.itc.resistorcalc.viewutils

import android.content.Context
import android.util.Log
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.itc.resistorcalc.R
import com.itc.resistorcalc.data.Constants.Companion.AMARILLO
import com.itc.resistorcalc.data.Constants.Companion.AZUL
import com.itc.resistorcalc.data.Constants.Companion.BLANCO
import com.itc.resistorcalc.data.Constants.Companion.CAFE
import com.itc.resistorcalc.data.Constants.Companion.DORADO
import com.itc.resistorcalc.data.Constants.Companion.GRIS
import com.itc.resistorcalc.data.Constants.Companion.LIGHT_MODE
import com.itc.resistorcalc.data.Constants.Companion.NARANJA
import com.itc.resistorcalc.data.Constants.Companion.NEGRO
import com.itc.resistorcalc.data.Constants.Companion.NIGHT_MODE
import com.itc.resistorcalc.data.Constants.Companion.PLATEADO
import com.itc.resistorcalc.data.Constants.Companion.ROJO
import com.itc.resistorcalc.data.Constants.Companion.VERDE
import com.itc.resistorcalc.data.Constants.Companion.VIOLETA

object ColorCardView {

    fun setResCalcCardViewColor(color: String?, context: Context?, indicator: CardView) {
        when (color) {
            NEGRO -> indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!, R.color.black
                )

            )
            CAFE -> indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!, R.color.brown
                )
            )

            ROJO -> indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!, R.color.red
                )
            )

            NARANJA -> indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!, R.color.orange
                )
            )

            AMARILLO -> indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!, R.color.yellow
                )
            )

            VERDE -> indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!, R.color.green
                )
            )

            AZUL -> indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!, R.color.blue
                )
            )

            VIOLETA -> indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!, R.color.violet
                )
            )

            GRIS -> indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!, R.color.grey
                )
            )

            BLANCO -> indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!, R.color.white
                )
            )

            DORADO -> indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!, R.color.gold
                )
            )

            PLATEADO -> indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!, R.color.silver
                )
            )

            context?.getString(R.string.tolerance_value_brown) -> indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!, R.color.brown
                )

            )
            context?.getString(R.string.tolerance_value_red) -> indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!, R.color.red
                )
            )

            context?.getString(R.string.tolerance_value_green) -> indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!, R.color.green
                )
            )

            context?.getString(R.string.tolerance_value_blue) -> indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!, R.color.blue
                )
            )

            context?.getString(R.string.tolerance_value_violet) -> indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!, R.color.violet
                )
            )

            context?.getString(R.string.tolerance_value_grey) -> indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!, R.color.grey
                )
            )

            context?.getString(R.string.tolerance_value_gold) -> indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!, R.color.gold
                )
            )

            context?.getString(R.string.tolerance_value_silver) -> indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!, R.color.silver
                )
            )

            context?.getString(R.string.ppm_value_brown) -> indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!, R.color.brown
                )
            )

            context?.getString(R.string.ppm_value_red) -> indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!, R.color.red
                )
            )

            context?.getString(R.string.ppm_value_orange) -> indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!, R.color.orange
                )
            )

            context?.getString(R.string.ppm_value_yellow) -> indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!, R.color.yellow
                )
            )

            context?.getString(R.string.ppm_value_blue) -> indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!, R.color.blue
                )
            )

            context?.getString(R.string.ppm_value_violet) -> indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    context!!, R.color.violet
                )
            )

            else -> {
                when (context?.resources?.configuration?.uiMode) {
                    NIGHT_MODE -> {
                        indicator.setCardBackgroundColor(
                            ContextCompat.getColor(
                                context, androidx.cardview.R.color.cardview_dark_background
                            )
                        )
                    }
                    LIGHT_MODE -> {
                        indicator.setCardBackgroundColor(
                            ContextCompat.getColor(
                                context, androidx.cardview.R.color.cardview_light_background
                            )
                        )
                    }
                }
            }
        }
    }
}