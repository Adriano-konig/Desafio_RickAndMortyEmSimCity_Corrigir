package br.com.zup.desafiorickandmortyemsimcity.ui.favorite.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.desafiorickandmortyemsimcity.data.model.PersonagensResult
import br.com.zup.desafiorickandmortyemsimcity.domain.model.SingleLiveEvent
import br.com.zup.desafiorickandmortyemsimcity.domain.usecase.PersonagenUseCase
import br.com.zup.desafiorickandmortyemsimcity.ui.FALHA_CARREGAR
import br.com.zup.desafiorickandmortyemsimcity.ui.FALHA_DESFAVORITA
import br.com.zup.desafiorickandmortyemsimcity.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private val personagemUseCase = PersonagenUseCase(application)
    val personagemListFavoriteState = SingleLiveEvent<ViewState<List<PersonagensResult>>>()
    val personagemDisfavorState = SingleLiveEvent<ViewState<PersonagensResult>>()

    fun disfavorPersonagens(personagem: PersonagensResult) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                   personagemUseCase.updateFavoritoPersonagens(personagem)
                }
                personagemDisfavorState.value = response
            } catch (ex: Exception) {
                personagemDisfavorState.value =
                    ViewState.Error(Throwable(FALHA_DESFAVORITA))
            }
        }
    }

    fun getAllPersonagensFavorited() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    personagemUseCase.getAllFavoritoPersonagens()
                }
                personagemListFavoriteState.value = response
            } catch (ex: Exception) {
                personagemListFavoriteState.value =
                    ViewState.Error(Throwable(FALHA_CARREGAR))
            }
        }
    }
}