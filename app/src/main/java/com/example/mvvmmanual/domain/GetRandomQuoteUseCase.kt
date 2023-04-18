package com.example.mvvmmanual.domain

import com.example.mvvmmanual.data.model.QuoteModel
import com.example.mvvmmanual.data.model.QuoteRepository
import com.example.mvvmmanual.domain.model.Quote

class GetRandomQuoteUseCase(private val repository: QuoteRepository) {
    suspend operator fun invoke(): List<Quote> = repository.getAllQuotesFromDatabase()
}