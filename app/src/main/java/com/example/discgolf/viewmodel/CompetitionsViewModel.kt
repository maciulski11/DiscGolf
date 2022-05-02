package com.example.discgolf.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.discgolf.repository.CompetitionsRepository
import com.example.discgolf.room.competitions.Competitions
import com.example.discgolf.room.player.Player
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking

class CompetitionsViewModel(application: Application): AndroidViewModel(application) {

    private var competitionsRepository: CompetitionsRepository = //-> tworzymy nasze repositorium
        CompetitionsRepository(application)

    private var allCompetitions: Deferred<LiveData<List<Competitions>>> = //->tworzymy zmienna ktora przechowuje
        competitionsRepository.getAllCompetitions()

    fun addCompetitions(competitions: Competitions) {
        competitionsRepository.addCompetitions(competitions)
    }

    fun getAllCompetitions(): LiveData<List<Competitions>> = runBlocking {
        allCompetitions.await() //nie mozemy z tego skorzystac bez blokowania watku
    }
}