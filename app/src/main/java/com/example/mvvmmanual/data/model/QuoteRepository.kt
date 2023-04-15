package com.example.mvvmmanual.data.model

import com.example.mvvmmanual.data.model.database.QuoteProvider
import com.example.mvvmmanual.data.model.network.QuoteService

/*Clase encargada de elegir si acceder a la parte network, o a la database*/
class QuoteRepository {
    private val api = QuoteService()

    suspend fun getAllQuotes():List<QuoteModel>{
        val response = api.getQuotes()
        //Guardamos las quotes en el provider (nuestra database)
        QuoteProvider.quotes = response
        return response
    }

    fun getRandomQuote () : QuoteModel = QuoteProvider.randomQuote()
}