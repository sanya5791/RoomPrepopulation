package com.example.akhutornoy.roomprepopulation.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CityDao {
    @Insert
    fun insert(city: City)

    @Update
    fun update(city: City)

    @Delete
    fun delete(city: City)

    @Query("SELECT * FROM City")
    fun getAll(): LiveData<List<City>>
}