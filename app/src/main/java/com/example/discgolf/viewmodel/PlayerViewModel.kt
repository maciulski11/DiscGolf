package com.example.discgolf.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.discgolf.repository.PlayerRepository
import com.example.discgolf.room.player.PlayerDatabase
import com.example.discgolf.room.player.Player
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//ViewmModel odpowiada za dostarczenie danych do interfejsu uzytkownika i jest posrednikiem pomiedzy
// repository a interfejsme uzytkownika
class PlayerViewModel(application: Application): AndroidViewModel(application)  {


    private var playerReposiotry: PlayerRepository = //-> tworzymy nasze repositorium
        PlayerRepository(application)

    private var allPlayer: Deferred<LiveData<List<Player>>> = //->tworzymy zmienna ktora przechowuje
        playerReposiotry.getAllPlayer() //naszych wszystkich pobranych ludzi dzieki funkcji get

    fun addPlayer(player: Player) {
        playerReposiotry.addPlayer(player)
    }

    fun getAllPlayer(): LiveData<List<Player>> = runBlocking {
        allPlayer.await() //nie mozemy z tego skorzystac bez blokowania watku
    }

    fun deletePlayer(player: Player){
        playerReposiotry.deletePlayer(player)
    }
}