package br.com.zup.desafiorickandmortyemsimcity.ui.quadrinhos.viewmodel

import android.app.Application
import androidx.lifecycle.*
import br.com.zup.desafiorickandmortyemsimcity.data.model.PersonagensResult
import br.com.zup.desafiorickandmortyemsimcity.domain.model.SingleLiveEvent
import br.com.zup.desafiorickandmortyemsimcity.domain.usecase.PersonagenUseCase
import br.com.zup.desafiorickandmortyemsimcity.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuadrinhosPersonagensRickAndMortyViewModel(application: Application) : AndroidViewModel(application) {

    private val personagemUsecase = PersonagenUseCase(application)
    val personagensList = SingleLiveEvent<ViewState<List<PersonagensResult>>>()

    fun getAllPersonagensNetwork(){
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO){
                    personagemUsecase.getAllPersonagensNetwork()
                }
                personagensList.value = response
            }catch (ex : Exception){
                personagensList.value = ViewState.Error(Throwable("ERRO"))
            }
        }
    }
}