package com.example.akhutornoy.roomprepopulation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.akhutornoy.roomprepopulation.db.Db

class ViewModelFactory(private val dataBase: Db) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(CitiesViewModel::class.java) ->
                CitiesViewModel(dataBase.cityDao())
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        } as T
    }
}