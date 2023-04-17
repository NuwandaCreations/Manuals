package com.example.mvvmmanual.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmmanual.data.model.QuoteModel
import com.example.mvvmmanual.data.model.database.QuoteProvider
import com.example.mvvmmanual.domain.GetQuotesUseCase
import com.example.mvvmmanual.domain.GetRandomQuoteUseCase
import kotlinx.coroutines.launch

/*El viewModel es la conexión entre el modelo y la vista. Las vistas se suscriben a sus respectivos
viewModels y estos al percatarse de que el modelo ha sido modificado lo notificarán a la vista. Los
viewModels llaman a determinados casos de uso para cada función (En este proyecto, los casos de uso
a su vez notifican al repository que eligirá si hacer llamada a network o database).*/
class QuoteViewModel(
    private val getQuotesUseCase: GetQuotesUseCase,
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase
) : ViewModel() {
    val quoteLiveData = MutableLiveData<QuoteModel>()
    val progressLiveData = MutableLiveData<Boolean>()


    /*Puesto que el viewModel tendrá que llamar al caso de uso que queremos usar y en este caso es
    una corrutina lo podremos llamar desde viewModelScope que esta preparado para trabajar automáticamente
    (tiene incorporado el control de ciclo de vida p.e.)*/
    fun onCreate() {
        viewModelScope.launch {
            val result = getQuotesUseCase()
            progressLiveData.postValue(true)

            if (!result.isNullOrEmpty()) {
                /*Posteamos el valor en el liveData para que el observer del view lo vea y cambie la
                vista.*/
                quoteLiveData.postValue(result[result.indices.random()])
                progressLiveData.postValue(false)
            }
        }
    }

    fun randomQuote() {
        progressLiveData.postValue(true)
        val result = getRandomQuoteUseCase()

        quoteLiveData.postValue(result)
        progressLiveData.postValue(false)
    }
}