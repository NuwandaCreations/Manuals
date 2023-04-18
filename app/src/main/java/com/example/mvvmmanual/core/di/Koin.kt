package com.example.mvvmmanual.core.di

import android.content.Context
import androidx.room.Room
import com.example.mvvmmanual.data.model.QuoteRepository
import com.example.mvvmmanual.data.model.database.dao.QuoteDao
import com.example.mvvmmanual.data.model.database.databases.QuoteDatabase
import com.example.mvvmmanual.data.model.network.QuoteApiClient
import com.example.mvvmmanual.data.model.network.QuoteService
import com.example.mvvmmanual.domain.GetQuotesUseCase
import com.example.mvvmmanual.domain.GetRandomQuoteUseCase
import com.example.mvvmmanual.ui.viewModel.QuoteViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val QUOTE_DATABASE_NAME = "quote_database"


val appModule = module {
    single {
        GetQuotesUseCase(get())
    }
    single {
        GetRandomQuoteUseCase(get())
    }
}

val dataModule = module {
    single {
        getRetrofit()
    }
    single {
        getApiClient(get())
    }
    single {
        QuoteService(get())
    }
    single {
        provideRoom(androidContext())
    }
    single {
        provideDAO(get())
    }
    single {
        QuoteRepository(get(), get())
    }
}

val viewModelModule = module {
    viewModel {
        QuoteViewModel(get(), get())
    }
}


fun getRetrofit(): Retrofit {
    return Retrofit
        .Builder()
        .baseUrl("https://drawsomething-59328-default-rtdb.europe-west1.firebasedatabase.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun getApiClient(retrofit: Retrofit): QuoteApiClient {
    return retrofit.create(QuoteApiClient::class.java)
}

fun provideRoom(context: Context): QuoteDatabase {
    return Room.databaseBuilder(context, QuoteDatabase::class.java, QUOTE_DATABASE_NAME).build()
}

fun provideDAO(db: QuoteDatabase): QuoteDao {
    return db.getQuoteDao()
}