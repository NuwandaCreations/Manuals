package com.example.mvvmmanual.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmmanual.data.model.QuoteModel
import com.example.mvvmmanual.domain.GetQuotesUseCase
import kotlinx.coroutines.launch

//El viewModel es la conexión entre el modelo y la vista. Las vistas se suscriben a sus respectivos
// viewModels y estos al percatarse de que el modelo ha sido modificado lo notificarán a la vista.
class QuoteViewModel :ViewModel() {
    val quoteLiveData = MutableLiveData<QuoteModel>()
    var getQuotesUseCase = GetQuotesUseCase()

    /*Puesto que el viewModel tendrá que llamar al caso de uso que queremos usar y en este caso es
    una corrutina lo podremos llamar desde viewModelScope que esta preparado para trabajar automáticamente
    (tiene incorporado el control de ciclo de vida p.e.)*/
    fun onCreate(){
        viewModelScope.launch {
            val result = getQuotesUseCase()

            if (!result.isNullOrEmpty()){
                //Posteamos el valor en el liveData para que el observer del view lo vea y cambie.
                quoteLiveData.postValue(result[(0..9).random()])
            }
        }
    }


    /*fun randomQuote(){
        val currentQuote = QuoteProvider.randomQuote()
        quoteLiveData.postValue(currentQuote)
    }*/
}