package com.example.akhutornoy.roomprepopulation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.akhutornoy.roomprepopulation.db.RoomDb

class ViewModelFactory(private val dataBase: RoomDb) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(CitiesViewModel::class.java) ->
                CitiesViewModel(dataBase.cityDao())
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        } as T
    }
}