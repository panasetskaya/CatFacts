package com.example.catfacts.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.catfacts.pojo.CatFact

@Database(entities = [CatFact::class], version = 8, exportSchema = false)
abstract class CatFactsDatabase: RoomDatabase() {
    companion object {
        private val LOCK = Any()
        private const val DB_NAME = "cat_facts.db"
        private var database: CatFactsDatabase? = null

        fun getInstance(context: Context): CatFactsDatabase? {
            synchronized(LOCK) {
                if (database==null) {
                    val instance = Room.databaseBuilder(context, CatFactsDatabase::class.java, DB_NAME).build()
                    database = instance
                    return instance
                } else {
                    return database}
            }
        }
    }
    abstract fun catFactDao(): CatFactDao
}