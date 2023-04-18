package com.example.mvvmmanual.domain.model

import com.example.mvvmmanual.data.model.QuoteModel
import com.example.mvvmmanual.data.model.database.entities.QuoteEntity

/*El modelo y la UI tienen que usar un modelo de datos diferente que la capa de datos. Esto se logra
mediante mappers. Así modelo y UI trabajan con un modelo de datos estático y si tenemos que cambiar
base de datos, retrofit... le va a dar igual.
Es un modelo simple, sin anotaciones, kotlin puro*/
data class Quote(
    val quote: String,
    val author: String
)

/*Los mapper son una función que nos permite cambiar el objeto por capas. Se encarga de transformar
la información de backend a nuestro modelo final. Los mapper hay que meterlos en la clase final (el
modelo al que queremos llegar) mediante funciones de extensión.*/
fun QuoteModel.toDomain() = Quote(quote, author)

fun QuoteEntity.toDomain() = Quote(quote, author)