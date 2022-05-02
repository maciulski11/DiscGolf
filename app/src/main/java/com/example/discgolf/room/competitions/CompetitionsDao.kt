package com.example.discgolf.room.competitions

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CompetitionsDao {

    @Insert
    suspend fun insert(competitions: Competitions)

    @Query("SELECT * FROM competitions ORDER BY competitionsId ASC")
    fun getAllCompetitions(): LiveData<List<Competitions>>
}