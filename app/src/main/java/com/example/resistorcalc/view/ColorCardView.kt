package com.example.resistorcalc.view

import android.content.Context
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.resistorcalc.R
import com.example.resistorcalc.model.Constants.Companion.AMARILLO
import com.example.resistorcalc.model.Constants.Companion.AZUL
import com.example.resistorcalc.model.Constants.Companion.BLANCO
import com.example.resistorcalc.model.Constants.Companion.CAFE
import com.example.resistorcalc.model.Constants.Companion.DORADO
import com.example.resistorcalc.model.Constants.Companion.GRIS
import com.example.resistorcalc.model.Constants.Companion.NARANJA
import com.example.resistorcalc.model.Constants.Companion.NEGRO
import com.example.resistorcalc.model.Constants.Companion.PLATEADO
import com.example.resistorcalc.model.Constants.Companion.ROJO
import com.example.resistorcalc.model.Constants.Companion.VERDE
import com.example.resistorcalc.model.Constants.Companion.VIOLETA

class ColorCardView {

    fun setCardViewColor(color: String, context: Context?, indicator: CardView){
        when(color){
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
        }
    }
}