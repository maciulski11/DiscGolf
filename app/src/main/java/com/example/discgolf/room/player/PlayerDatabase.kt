package com.example.discgolf.room.player

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//TA KLASA MUSI BYC ABSTRAKCYJNA BO ROOMDATABSE TEGO WYMAGA!

//entities -> co jest wejsciem dla bazy danych
// version -> jaka to jest wersja bazy danych, im wiecej tym wieksza wersja
//exportSchema -> export danych zawsze jest true i nie trzeba go pisac chyba ze chcemy flase
@Database(entities = [Player::class], version = 1, exportSchema = true)
abstract class PlayerDatabase : RoomDatabase() {

    //tworzymy abstrakcyjna fun ktora zwraca interfejs (uzytkownika lub objekt dostepu do danych)
    abstract fun playerDao(): PlayerDao

    //tworzymy obiekt naszej bazy danych i mozemy stworzyc go tylko raz a pozniej go edytowac i zmieniac
    companion object {

        private var INSTANCE: PlayerDatabase? =
            null //instancja jest na poczatku nullem bo nieistnieje

        //nastepnie udostepniamy funkcje(instancje) ktora nam zwroci PlayerDatatbase:
        fun getDatebase(context: Context): PlayerDatabase {
            var instance = INSTANCE

            if (instance == null) {
                instance = Room.databaseBuilder(//tworzymy zsynchronizowany blok zeby wszystko nie wykonalo sie naraz
                    context.applicationContext, //podajemy context ktory jest w argumencie funkcji
                    PlayerDatabase::class.java, //nastepnie nasza klase
                    "players"//nazwe naszej tabeli
                ).build()
            }
            return instance //musimy go zwrocic

        }

        //funkcja ktora nam skasuje to co stworzylismy wyrzej
        fun deleteInstanceOfDatabase() {
            INSTANCE = null
        }
    }


}