package com.example.mvvmmanual.domain

import com.example.mvvmmanual.data.model.QuoteModel
import com.example.mvvmmanual.data.model.QuoteRepository
import com.example.mvvmmanual.data.model.database.entities.toDatabase
import com.example.mvvmmanual.domain.model.Quote

//Un caso de uso es el encargado de realizar una única acción.
class GetQuotesUseCase (private val repository : QuoteRepository){
    /*Invoke: Se trata de una función que siempre que se cree una instancia de la clase GetQuotesUseCase
    se ejecutará directamente esta función sin necesidad de poner GetQuotesUseCase.mifunción()*/
    suspend operator fun invoke(): List<Quote>?{
        val quotes = repository.getAllQuotesFromApi()
        return if (quotes.isNotEmpty()){
            /*Primero eliminamos la base de datos, si no se guardarán la quotes todas las veces que
            usemos el caso de uso*/
            repository.clearQuotes()
            repository.insertAllQuotes(quotes.map { it.toDatabase() })
            //si ponemos el return al principio, me retornará la última línea
            quotes
        }else{
            repository.getAllQuotesFromDatabase()
        }
    }
}