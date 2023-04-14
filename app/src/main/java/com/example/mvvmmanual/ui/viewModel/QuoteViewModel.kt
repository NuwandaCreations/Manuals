package com.example.mvvmmanual.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmmanual.data.model.QuoteModel
import com.example.mvvmmanual.data.model.QuoteProvider

//El viewModel es la conexión entre el modelo y la vista. Las vistas se suscriben a sus respectivos
// viewModels y estos al percatarse de que el modelo ha sido modificado lo notificarán a la vista.
class QuoteViewModel :ViewModel() {
    val quoteLiveData = MutableLiveData<QuoteModel>()

    fun randomQuote(){
        val currentQuote = QuoteProvider.randomQuote()
        quoteLiveData.postValue(currentQuote)
    }
}