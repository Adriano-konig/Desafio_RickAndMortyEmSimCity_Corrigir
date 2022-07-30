package br.com.zup.desafiorickandmortyemsimcity.domain.repository

import br.com.zup.desafiorickandmortyemsimcity.data.datasource.remote.RetrofitService
import br.com.zup.desafiorickandmortyemsimcity.data.datasource.local.dao.PersonagemDao
import br.com.zup.desafiorickandmortyemsimcity.data.model.PersonagensResponse
import br.com.zup.desafiorickandmortyemsimcity.data.model.PersonagensResult

class PersonagensRepository(private val personagemDao: PersonagemDao) {

    fun getAllPersonagens(): List<PersonagensResult> = personagemDao.getAllPersonagens()

    fun insertAllPersonagensDataBase(personagensList: List<PersonagensResult>){
        personagemDao.insertAllPersonagens(personagensList)
    }

    suspend fun getAllPersonagensNetwork(): PersonagensResponse{
        return RetrofitService.apiService.getAllPersonagensNetwork()
    }

    suspend fun getAllFavorito(): List<PersonagensResult> = personagemDao.getAllFavoritoPersonagem()

    suspend fun updateFavoritoPersonagens(personagem: PersonagensResult){
        personagemDao.updateFavoritoPersonagens(personagem)
    }
}