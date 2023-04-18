package com.example.mvvmmanual.data.model.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mvvmmanual.data.model.database.entities.QuoteEntity

@Dao
interface QuoteDao {
    @Insert
    suspend fun insertAllQuotes(quotes:List<QuoteEntity>)

    @Query("SELECT * FROM quote_table ORDER BY author DESC")
    suspend fun getAllQuotes():List<QuoteEntity>
}