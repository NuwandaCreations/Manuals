package com.example.mvvmmanual.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.mvvmmanual.databinding.ActivityMainBinding
import com.example.mvvmmanual.ui.viewModel.QuoteViewModel

class MainActivity : AppCompatActivity() {
    //El view es la parte de la UI, los XML, las activities y los fragments. Estos actuarán como siempre,
    // al pulsar un botón se suscribirán al view model y este les dirá cuando y como pintar.
    private lateinit var binding: ActivityMainBinding
    private val quoteViewModel: QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*Con la variable quoteViewModel engancho el quoteLiveData a esta variable que observará
        (función observe) cualquier cambio que se produzca en el liveData y actuará según lo que
        estipule en la función Observer*/
        quoteViewModel.quoteLiveData.observe(this, Observer {
            binding.tvQuote.text = it.quote
            binding.tvAuthor.text = it.author
        })

        quoteViewModel.progressLiveData.observe(this, Observer {
            binding.progressMain.isVisible = it
        })

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