package com.example.mvvmmanual.core.di

import com.example.mvvmmanual.data.model.QuoteRepository
import com.example.mvvmmanual.data.model.database.QuoteProvider
import com.example.mvvmmanual.data.model.network.QuoteApiClient
import com.example.mvvmmanual.data.model.network.QuoteService
import com.example.mvvmmanual.domain.GetQuotesUseCase
import com.example.mvvmmanual.domain.GetRandomQuoteUseCase
import com.example.mvvmmanual.ui.viewModel.QuoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
        getApiCLient(get())
    }
    single {
        QuoteService(get())
    }
    single {
        QuoteProvider()
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

fun getApiCLient(retrofit: Retrofit): QuoteApiClient {
    return retrofit.create(QuoteApiClient::class.java)
}
