package br.com.zup.desafiorickandmortyemsimcity.domain.usecase

import android.app.Application
import br.com.zup.desafiorickandmortyemsimcity.data.datasource.local.database.PersonagemDatabase
import br.com.zup.desafiorickandmortyemsimcity.data.model.PersonagensResult
import br.com.zup.desafiorickandmortyemsimcity.domain.repository.PersonagensRepository
import br.com.zup.desafiorickandmortyemsimcity.ui.viewstate.ViewState

class PersonagenUseCase(application: Application) {

    private val personagenDao = PersonagemDatabase.getPersonagensDataBase(application).personagensDao()
    private val personagensRepository = PersonagensRepository(personagenDao)

    suspend fun getAllPersonagensNetwork(): ViewState<List<PersonagensResult>>{
        return try {
            val personagens = personagensRepository.getAllPersonagensNetwork()
            personagensRepository.insertAllPersonagensDataBase(personagens.personagensResult)
            ViewState.Success(personagens.personagensResult)
        }catch (ex : Exception){
            getAllPersonagem()
        }
    }

    suspend fun getAllPersonagem():ViewState<List<PersonagensResult>>{
        return try {
            val personagem = personagensRepository.getAllPersonagens()
            if (personagem.isEmpty()){
                ViewState.EmptyList(personagem)
            }else{
                ViewState.Success(personagem)
            }
        }catch (ex : Exception){
            ViewState.Error(Exception("Erro"))
        }
    }

    suspend fun getAllFavoritoPersonagens(): ViewState<List<PersonagensResult>>{
        return try {
            val personagem = personagensRepository.getAllFavorito()
            if (personagem.isEmpty()){
                ViewState.EmptyList(personagem)
            }else{
                ViewState.Success(personagem)
            }
        }catch (ex : Exception){
            ViewState.Error(Exception("erro"))
        }
    }

    suspend fun updateFavoritoPersonagens(personagem: PersonagensResult): ViewState<PersonagensResult>{
        return try {
            personagensRepository.updateFavoritoPersonagens(personagem)
            ViewState.Success(personagem)
        }catch (ex : Exception){
            ViewState.Error(Exception("erro"))
        }
    }
}