package br.com.zup.desafiorickandmortyemsimcity.data.datasource.local.dao

import androidx.room.*
import br.com.zup.desafiorickandmortyemsimcity.data.model.PersonagensResult

@Dao
interface PersonagemDao {

    @Query("SELECT * FROM characters ORDER BY name ASC")
    fun getAllPersonagens(): List<PersonagensResult>

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insertAllPersonagens(personagensList: List<PersonagensResult>)

    @Query("SELECT * FROM characters WHERE isFavorite = 1")
    fun getAllFavoritoPersonagem(): List<PersonagensResult>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateFavoritoPersonagens(personagensResult: PersonagensResult)
}