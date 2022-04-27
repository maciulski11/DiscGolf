package com.example.discgolf.room.player

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.discgolf.room.player.Player

//Data Access Object -> DAO
@Dao //ten interfejs mowi nam na jakie operacje w bazie danych pozwalamy
interface PlayerDao {

    //Podstawowe 3 operacje:
    @Insert(onConflict =  OnConflictStrategy.IGNORE)//-> oznacza ze np. jak pojawi sie dokladnie ten sam
    suspend fun insert(player: Player)                  //player to ma po prostu to zignorujemy

    @Update
    fun update(player: Player)

    @Delete
    fun delete(player: Player)

    //Query to zapytanie aby mozna bylo cos wyciagnac z naszej bazy danych
    //najprostsze zapytanie czyli SELECT(wybierz), *(wszystko), FROM(skad), nazwa naszej tabeli
    @Query("SELECT * FROM players ORDER BY playerId ASC")//-> oreder.. oddaj players w porzadku jak ich dodalismy rosnaco
    fun getAllPlayer(): LiveData<List<Player>> // LiveData dlatego bo uzywamy modelu MVVM
    //^^ ta funkcja ma nam zwrocic ^^ liste osob

    //usun wszystkie dane
    @Query("DELETE FROM players")
    fun deleteAllRows()
    //^funcka do naszego zapytania zeby wszystko usunela i ona nic nie zwraca


}