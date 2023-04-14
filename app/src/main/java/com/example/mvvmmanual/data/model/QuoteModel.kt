package com.example.mvvmmanual.data.model

import com.google.gson.annotations.SerializedName

//En el model tendremos la parte de datos, cuando recuperamos de una base de datos o de un servicio
// web, en este caso un servicio web de una API.
data class QuoteModel(
    @SerializedName("quote") val quote: String,
    @SerializedName("author")val author: String
)
