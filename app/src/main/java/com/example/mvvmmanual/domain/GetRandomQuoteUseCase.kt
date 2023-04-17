package com.example.mvvmmanual.domain

import com.example.mvvmmanual.data.model.QuoteModel
import com.example.mvvmmanual.data.model.QuoteRepository
import com.example.mvvmmanual.data.model.database.QuoteProvider

class GetRandomQuoteUseCase(private val repository: QuoteRepository) {
    operator fun invoke(): QuoteModel = repository.getRandomQuote()
}