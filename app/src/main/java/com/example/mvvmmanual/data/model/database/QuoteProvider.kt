package com.example.mvvmmanual.data.model.database

import com.example.mvvmmanual.data.model.QuoteModel

class QuoteProvider {
        var quotes: List<QuoteModel> = emptyList()
        fun randomQuote() = quotes[(quotes.indices).random()]
}