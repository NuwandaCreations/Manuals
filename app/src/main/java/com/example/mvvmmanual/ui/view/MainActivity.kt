package com.example.mvvmmanual.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.mvvmmanual.databinding.ActivityMainBinding
import com.example.mvvmmanual.ui.viewModel.QuoteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    /*Lo primero para implementar inyección de dependencias será:
    1. Incluir la dependencia de Koin en el build gradle
    2. Crear una clase Aplication para start koin{} y NOMBRARLA EN EL MANIFEST!!
    3. Creamos un fichero con los módulos que vamos a querer inyectar. IMPORTANTE: Hay que declarar
     los módulos en la clase Aplication()
    4. Para que una clase sea inyectada se tiene que cumplir que esté generado el módulo que queremos
    inyectar e introducirlo en la descripción de la propia clase.
    5a. Para inyectar cualquier módulo en la activity/fragment se usa private val foo: Foo by inject().
    5b. Para inyectar un viewModel también hay que generar el módulo y declararlo en la activity/fragment
    así: private val quoteViewModel: QuoteViewModel by viewModel(). OJO NO ES viewModelSSS()*/

    private lateinit var binding: ActivityMainBinding
    private val quoteViewModel: QuoteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*Con la variable quoteViewModel engancho el quoteLiveData a esta variable que observará
        (función observe) cualquier cambio que se produzca en el liveData y actuará según lo que
        estipule en la función Observer*/
        quoteViewModel.quoteLiveData.observe(this) {
            binding.tvQuote.text = it.quote
            binding.tvAuthor.text = it.author
        }

        quoteViewModel.progressLiveData.observe(this) {
            binding.progressMain.isVisible = it
        }

        quoteViewModel.onCreate()

        /*Aquí es cuando la parte view notifica al liveData que ha habido un cambio en la UI, que se
        ponga en contacto con el modelo, realicen las modificaciones pertinentes y generen un
        cambio en el liveData para que mi variable anterior (quoteViewModel) pueda observar el
        cambio y actuar en consecuencia. IMPORTANTE: se llama desde mi propia variable de la
        activity*/
        binding.activityListener.setOnClickListener {
            quoteViewModel.randomQuote()
        }
    }
}