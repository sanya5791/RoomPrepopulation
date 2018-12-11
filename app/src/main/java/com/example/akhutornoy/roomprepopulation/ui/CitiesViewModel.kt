package com.example.akhutornoy.roomprepopulation.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.akhutornoy.roomprepopulation.App
import com.example.akhutornoy.roomprepopulation.db.City
import com.example.akhutornoy.roomprepopulation.db.CityDao
import com.github.ajalt.timberkt.Timber
import java.util.*

class CitiesViewModel(
    private val cityDao: CityDao
) : ViewModel() {

    private lateinit var _cities: LiveData<List<City>>
    val cities
        get() = _cities

    private val _locale = MutableLiveData<String>()
    val locale
        get() = _locale

    private val _showError = MutableLiveData<String>()
    val showError
        get() = _showError

    fun loadData() {
        loadLocale()
        loadCities()
    }

    private fun loadLocale() {
        _locale.value = Locale.getDefault().country
    }

    private fun loadCities() {
        _cities = cityDao.getAll()
    }
}