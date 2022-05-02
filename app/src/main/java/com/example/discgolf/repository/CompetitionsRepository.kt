package com.example.discgolf.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.discgolf.room.competitions.Competitions
import com.example.discgolf.room.competitions.CompetitionsDao
import com.example.discgolf.room.competitions.CompetitionsDatabase
import com.example.discgolf.room.player.Player
import kotlinx.coroutines.*

class CompetitionsRepository(application: Application) {

    private var competitionsDao: CompetitionsDao

    init { // na starcie w init tworzymy nasza baze danych wykorzystujac kod ktory
        val database = CompetitionsDatabase   //napisalismy wczesniej
            .getDatebase(application.applicationContext)
        //a gdy juz uchwycimy nasza baze danych to uzywamy metody peopleDao:
        competitionsDao = database.competitionsDao() //ktora nam zwroci wlasnie ten interfejs
    }

    fun addCompetitions(competitions: Competitions) : Job =
        CoroutineScope(Dispatchers.IO).launch {
            competitionsDao.insert(competitions) //wywolujemy fun z interfeujsu
        }

    fun getAllCompetitions(): Deferred<LiveData<List<Competitions>>> =
        CoroutineScope(Dispatchers.IO).async {
            competitionsDao.getAllCompetitions()
        }

}