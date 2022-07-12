package com.itc.resistorcalc.viewutils

import android.content.Context
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

    fun setCardViewColor(color: String?, context: Context?, indicator: CardView) {
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