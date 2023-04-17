package com.example.mvvmmanual

import android.app.Application
import com.example.mvvmmanual.core.di.appModule
import com.example.mvvmmanual.core.di.dataModule
import com.example.mvvmmanual.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MvvmManualApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            //Para ver los errores en el logcat
            androidLogger()
            //Le pasamos al injector la referencia del contexto de nuestra app para que después él nos la provea donde nos haga falta
            androidContext(this@MvvmManualApp)
            //Añadimos nuestros módulos donde definimos las  dependencias
            modules(appModule, dataModule, viewModelModule)
        }
    }
}