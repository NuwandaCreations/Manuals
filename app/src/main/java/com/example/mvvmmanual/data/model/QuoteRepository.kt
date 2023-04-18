package com.example.mvvmmanual.data.model

import com.example.mvvmmanual.data.model.database.QuoteProvider
import com.example.mvvmmanual.data.model.network.QuoteService

/*Clase encargada de elegir si acceder a la parte network, o a la database*/
class QuoteRepository(
    private val api: QuoteService,
    private val quoteProvider: QuoteProvider
) {
    suspend fun getAllQuotes(): List<QuoteModel> {
        val response = api.getQuotes()
        //Guardamos las quotes en el provider (nuestra database)
        quoteProvider.quotes = response
        return response
    }

    fun getRandomQuote(): QuoteModel = quoteProvider.randomQuote()
}