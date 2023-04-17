package com.example.mvvmmanual.data.model.network

import com.example.mvvmmanual.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteService (private val retrofit: QuoteApiClient){
    /*Hacemos la llamada a Retrofit mediante withContext(Dispatchers.IO) para correr en hilo
    secundario. Anteriormente usado CoroutineScope.*/
    suspend fun getQuotes() : List<QuoteModel>{
        return withContext(Dispatchers.IO){
            val myResponse = retrofit.getAllQuotes()
            myResponse.body() ?: emptyList()
        }
    }
}