package com.example.discgolf.room.competitions

import androidx.room.Entity
import androidx.room.PrimaryKey

//Entity -> jest wejsciem dla naszej  bazy danych competitions
@Entity(tableName = "competitions") //nazywamy tabele z danymi

data class Competitions(
    var nameCompetitions: String,
    var amountWells: String
) {

    //tworzenie unikatowego kodu
    @PrimaryKey(autoGenerate = true) //klucz ktory utworzy unikatowe id
                    //^^ true = kazdy nowy wiersz dostaje nowe id o 1 wieksze dla kazdego wiersza

    var competitionsId: Int = 0
}