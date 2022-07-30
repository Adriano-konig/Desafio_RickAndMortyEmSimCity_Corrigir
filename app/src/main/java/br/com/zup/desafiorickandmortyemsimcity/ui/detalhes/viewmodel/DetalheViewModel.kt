package br.com.zup.desafiorickandmortyemsimcity.ui.detalhes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.desafiorickandmortyemsimcity.data.model.PersonagensResult
import br.com.zup.desafiorickandmortyemsimcity.domain.model.SingleLiveEvent
import br.com.zup.desafiorickandmortyemsimcity.domain.usecase.PersonagenUseCase
import br.com.zup.desafiorickandmortyemsimcity.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetalheViewModel(application: Application): AndroidViewModel(application) {

    private val personagenUseCase = PersonagenUseCase(application)
    private val personagemFavorito = SingleLiveEvent<ViewState<PersonagensResult>>()

    fun updateFavoritePersonagem(personagensResult: PersonagensResult){
        viewModelScope.launch {
            try {
                val personagem = withContext(Dispatchers.IO){
                    personagenUseCase.updateFavoritoPersonagens(personagensResult)
                }
                personagemFavorito.value = personagem
            }catch (ex: Exception){
                personagemFavorito.value = ViewState.Error(Throwable("erro"))
            }
        }
    }
}