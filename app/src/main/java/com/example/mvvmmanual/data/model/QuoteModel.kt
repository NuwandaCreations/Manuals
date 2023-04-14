package com.example.mvvmmanual.data.model

//En el model tendremos la parte de datos, cuando recuperamos de una base de datos o de un servicio
// web, o como en este caso, creando nuestro propio modelo de datos con un provider.
data class QuoteModel(
    val quote: String,
    val author: String
)
