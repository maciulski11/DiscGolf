package com.example.discgolf.room.hole

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Hole::class], version = 1, exportSchema = true)
abstract class HoleDatabase: RoomDatabase() {
}