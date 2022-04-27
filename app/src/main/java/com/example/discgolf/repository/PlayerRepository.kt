package com.example.discgolf.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.discgolf.room.player.Player
import com.example.discgolf.room.player.PlayerDao
import com.example.discgolf.room.player.PlayerDatabase
import kotlinx.coroutines.*

//klasa ktora decyduje o tym jakie informacje przekazac dalej, zeby pomiedzy
//bazami danych nie doszlo do desynchronizacji i ta kalasa decyduje co ma przechodzic dalej
class PlayerRepository(application: Application) {

    private var playerDao: PlayerDao // zmienna ktora przechowa nasz interfejs

    init { // na starcie w init tworzymy nasza baze danych wykorzystujac kod ktory
        val database = PlayerDatabase   //napisalismy wczesniej
            .getDatebase(application.applicationContext)
        //a gdy juz uchwycimy nasza baze danych to uzywamy metody peopleDao:
        playerDao = database.playerDao() //ktora nam zwroci wlasnie ten interfejs
    }

    //nastepnie udostepniamy w repository te funkcje ktore udostepnilismy w interfejsie
    fun addPlayer(player: Player) : Job=
        CoroutineScope(Dispatchers.IO).launch {
            playerDao.insert(player) //wywolujemy fun z interfeujsu
    }

    fun deletePlayer(player: Player) : Job=
        CoroutineScope(Dispatchers.IO).launch {
            playerDao.delete(player)
        }

    fun getAllPlayer(): Deferred<LiveData<List<Player>>> =
        CoroutineScope(Dispatchers.IO).async {
            playerDao.getAllPlayer()
        }
}