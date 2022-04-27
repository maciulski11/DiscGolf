package com.example.discgolf.room.player

import androidx.room.Entity
import androidx.room.PrimaryKey

//Entity -> jest wejsciem dla naszej bazy danych users
@Entity(tableName = "players") //nazywamy tabele z danymi

data class Player(var player: String) {
    //tworzenie unikatowego kodu
    @PrimaryKey(autoGenerate = true) //klucz ktory utworzy unikatowe id
    //^^ true = kazdy nowy wiersz dostaje nowe id o 1 wieksze dla kazdego wiersza
    var playerId: Int = 0
}
