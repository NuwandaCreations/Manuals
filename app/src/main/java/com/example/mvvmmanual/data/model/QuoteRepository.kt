package com.example.mvvmmanual.data.model

import com.example.mvvmmanual.data.model.database.dao.QuoteDao
import com.example.mvvmmanual.data.model.database.entities.QuoteEntity
import com.example.mvvmmanual.data.model.network.QuoteService
import com.example.mvvmmanual.domain.model.Quote
import com.example.mvvmmanual.domain.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/*Clase encargada de elegir si acceder a la parte network, o a la database*/
class QuoteRepository(
    private val api: QuoteService,
    private val quoteDao: QuoteDao
) {
    suspend fun getAllQuotesFromApi(): List<Quote> {
        /*El repositorio es el encargado exclusivamente de acceder a internet y se las devuelve al
        dominio. Será el caso de uso el encargado de guardarla en una base de datos o hacer lo que quiera*/
        val response = api.getQuotes()
        //Uso del mapper para transformar QuoteModel en Quote
        return response.map { it.toDomain() }
    }

    suspend fun getAllQuotesFromDatabase(): List<Quote> {
        //Uso del mapper para transformar QuoteEntity en Quote
        return quoteDao.getAllQuotes().map { it.toDomain() }
    }

    suspend fun insertAllQuotes(quotes : List<QuoteEntity>){
        return quoteDao.insertAllQuotes(quotes)
    }

    suspend fun clearQuotes(){
        return quoteDao.deleteAllQuotes()
    }
}