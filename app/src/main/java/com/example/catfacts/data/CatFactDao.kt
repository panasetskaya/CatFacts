package com.example.catfacts.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.catfacts.pojo.CatFact

@Dao
interface CatFactDao {

    @Query("SELECT * FROM catfact")
    fun getAllFacts(): LiveData<List<CatFact>>

    @Query("DELETE FROM catfact")
    fun deleteAllFacts()

    @Insert
    fun insertFact(catFact: CatFact)

    @Delete
    fun delete(catFact: CatFact)



}