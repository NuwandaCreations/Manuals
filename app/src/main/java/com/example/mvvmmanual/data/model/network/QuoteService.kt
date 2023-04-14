package com.example.mvvmmanual.data.model.network

import com.example.mvvmmanual.core.RetrofitHelper
import com.example.mvvmmanual.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteService {
    /*Anteriormente creabamos el objeto Retrofit en la activity, pero basandonos en los principios
    SOLID será mejor crearlo en esta clase y usarlo desde donde queramos.*/
    val retrofit = RetrofitHelper.getRetrofit()

    /*Hacemos la llamada a Retrofit mediante withContext(Dispatchers.IO) para correr en hilo
    secundario. Anteriormente usado CoroutineScope.*/
    suspend fun getQuotes() : List<QuoteModel>{
        return withContext(Dispatchers.IO){
            val myResponse = retrofit.create(QuoteApiClient::class.java).getAllQuotes()
            myResponse.body() ?: emptyList()
        }
    }
}