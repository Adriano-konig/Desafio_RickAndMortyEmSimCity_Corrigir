package br.com.zup.desafiorickandmortyemsimcity.data.model


import com.google.gson.annotations.SerializedName

data class PersonagensResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val personagensResult: List<PersonagensResult>
)