package br.com.zup.desafiorickandmortyemsimcity.data.datasource.remote

import br.com.zup.desafiorickandmortyemsimcity.data.model.PersonagensResponse
import retrofit2.http.GET


interface PersonagenAPI {

    @GET("character")
    suspend fun getAllPersonagensNetwork(): PersonagensResponse
}