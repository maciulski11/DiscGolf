package com.example.discgolf.room.hole

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface HoleDao {

    @Insert(onConflict =  OnConflictStrategy.IGNORE)//-> oznacza ze np. jak pojawi sie dokladnie ten sam
    suspend fun insert(hole: Hole)
}