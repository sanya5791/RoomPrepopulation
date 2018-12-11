package com.example.akhutornoy.roomprepopulation.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class City(
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}