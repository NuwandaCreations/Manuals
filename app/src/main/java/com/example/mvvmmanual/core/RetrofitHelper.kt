package com.example.mvvmmanual.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*Usaremos el directorio core para crear todos aquellos archivos que no encagen en los otros
directorios, como este objeto de ayuda para crear Retrofit*/
object RetrofitHelper {
    fun getRetrofit(): Retrofit = Retrofit
        .Builder()
        .baseUrl("https://drawsomething-59328-default-rtdb.europe-west1.firebasedatabase.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}