package com.example.mvvmmanual.data.model.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mvvmmanual.domain.model.Quote

@Entity(tableName = "quote_table")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id") val id: Int = 0,
    @ColumnInfo("quote") val quote: String,
    @ColumnInfo("author") val author: String
)

fun Quote.toDatabase() = QuoteEntity(quote = quote, author = author)