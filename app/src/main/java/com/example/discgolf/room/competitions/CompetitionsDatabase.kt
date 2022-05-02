package com.example.discgolf.room.competitions

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.discgolf.room.player.Player
import com.example.discgolf.room.player.PlayerDatabase

@Database(entities = [Competitions::class], version = 1, exportSchema = true)
abstract class CompetitionsDatabase: RoomDatabase() {

    abstract fun competitionsDao(): CompetitionsDao

    companion object{

        private var INSTANCE: CompetitionsDatabase? = null

        fun getDatebase(context: Context): CompetitionsDatabase {
            var instance = INSTANCE

            if (instance == null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    CompetitionsDatabase::class.java,
                    "competitions"
                ).build()
            }

            return instance
        }
    }

}