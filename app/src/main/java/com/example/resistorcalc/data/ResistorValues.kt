package com.example.resistorcalc.data

object ResistorValues {
    val bandValues = mapOf(
        "Negro" to 0.0,
        "Cafe" to 1.0,
        "Rojo" to 2.0,
        "Naranja" to 3.0,
        "Amarillo" to 4.0,
        "Verde" to 5.0,
        "Azul" to 6.0,
        "Violeta" to 7.0,
        "Gris" to 8.0,
        "Blanco" to 9.0
    )

    val multiplierValues = mapOf(
        "Negro" to 1.0,
        "Cafe" to 10.0,
        "Rojo" to 100.0,
        "Naranja" to 1000.0,
        "Amarillo" to 10000.0,
        "Verde" to 100000.0,
        "Azul" to 1000000.0,
        "Violeta" to 10000000.0,
        "Gris" to 100000000.0,
        "Blanco" to 1000000000.0,
        "Dorado" to 0.1,
        "Plateado" to 0.01
    )

    val toleranceValues = mapOf(
        "Cafe" to 1.0,
        "Rojo" to 2.0,
        "Verde" to 0.5,
        "Azul" to 0.25,
        "Violeta" to 0.1,
        "Gris" to 0.05,
        "Dorado" to 5.0,
        "Plateado" to 10.0
    )

    val ppmValues = mapOf(
        "Cafe" to 100.0,
        "Rojo" to 50.0,
        "Naranja" to 15.0,
        "Amarillo" to 25.0,
        "Azul" to 10.0,
        "Violeta" to 5.0
    )

    val valuesToBands = mapOf(
        "0" to "Negro",
        "1" to "Cafe",
        "2" to "Rojo",
        "3" to "Naranja",
        "4" to "Amarillo",
        "5" to "Verde",
        "6" to "Azul",
        "7" to "Violeta",
        "8" to "Gris",
        "9" to "Blanco"
    )

    val valuesToMultiplierBand = mapOf(
        1.0 to "Negro",
        10.0 to "Cafe",
        100.0 to "Rojo",
        1000.0 to "Naranja",
        10000.0 to "Amarillo",
        100000.0 to "Verde",
        1000000.0 to "Azul",
        10000000.0 to "Violeta",
        100000000.0 to "Gris",
        1000000000.0 to "Blanco",
        0.1 to "Dorado",
        0.01 to "Plateado"
    )

    val valuesToTolerance = mapOf(
        1.0 to "Cafe",
        2.0 to "Rojo",
        0.5 to "Verde",
        0.25 to "Azul",
        0.1 to "Violeta",
        0.05 to "Gris",
        5.0 to "Dorado",
        10.0 to "Plateado"
    )

    val valuesToPPM = mapOf(
        100.0 to "Cafe",
        50.0 to "Rojo",
        15.0 to "Naranja",
        25.0 to "Amarillo",
        10.0 to "Azul",
        5.0 to "Violeta"
    )
}