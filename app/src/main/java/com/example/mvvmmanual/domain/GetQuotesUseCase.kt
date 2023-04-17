package com.example.mvvmmanual.domain

import com.example.mvvmmanual.data.model.QuoteModel
import com.example.mvvmmanual.data.model.QuoteRepository

//Un caso de uso es el encargado de realizar una única acción.
class GetQuotesUseCase (private val repository : QuoteRepository){
    /*Invoke: Se trata de una función que siempre que se cree una instancia de la clase GetQuotesUseCase
    se ejecutará directamente esta función sin necesidad de poner GetQuotesUseCase.mifunción()*/
    suspend operator fun invoke(): List<QuoteModel>?{
        return repository.getAllQuotes()
    }
}