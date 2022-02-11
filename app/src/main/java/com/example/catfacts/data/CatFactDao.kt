package com.example.catfacts.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.catfacts.pojo.CatFact
import com.example.catfacts.pojo.FavouriteFact

@Dao
interface CatFactDao {

    @Query("SELECT * FROM catfact")
    fun getAllFacts(): LiveData<List<CatFact>>

    @Query("SELECT * FROM favouritefact")
    fun getAllFavouriteFacts(): LiveData<List<FavouriteFact>>

    @Query("DELETE FROM catfact")
    fun deleteAllFacts()

    @Query("DELETE FROM favouritefact")
    fun deleteAllFavouriteFacts()

    @Query("SELECT EXISTS (SELECT * FROM favouritefact WHERE FactId==:requiredId)")
    fun existsInFavourites(requiredId: String): Boolean

    @Query("SELECT * FROM catfact WHERE FactId==:requiredId")
    fun getCatFactById(requiredId: String): LiveData<CatFact>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFact(catFact: CatFact)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavouriteFact(favouriteFact: FavouriteFact)

    @Delete
    fun deleteCatFact(catFact: CatFact)

    @Delete
    fun deleteFavouriteFact(favouriteFact: FavouriteFact)
}