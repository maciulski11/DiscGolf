package com.example.discgolf.room.hole

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "holes")
data class Hole(
    var nrHole: Int,
    var movements: Int
){
    @PrimaryKey(autoGenerate = true)
    var holeId: Int = 0
}