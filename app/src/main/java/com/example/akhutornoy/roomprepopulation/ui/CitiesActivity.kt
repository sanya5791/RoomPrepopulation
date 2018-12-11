package com.example.akhutornoy.roomprepopulation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.akhutornoy.roomprepopulation.Injections
import com.example.akhutornoy.roomprepopulation.R
import com.example.akhutornoy.roomprepopulation.db.City

import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.toolbar.*

class CitiesActivity : AppCompatActivity() {
    private val adapter: CitiesAdapter by lazy { CitiesAdapter() }

    private val viewModel: CitiesViewModel by lazy { Injections.provideCitiesViewModel(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewModel.loadData()
        initLiveDataObservers()

        initViews()
    }

    private fun initViews() {
        initRecyclerView()
    }

    private fun initLiveDataObservers() {
        viewModel.locale.observe(this, Observer { locale -> showLocale(locale) })
        viewModel.cities.observe(this, Observer { cities -> showCities(cities) })
        viewModel.showError.observe(this, Observer { error -> showError(error) })
    }

    private fun showLocale(locale: String) {
        val msg = getString(R.string.locale_tile, locale)
        localeTitleTextView.text = msg
    }

    private fun showCities(cities: List<City>) {
        adapter.setCities(cities)
    }

    private fun showError(error: String){
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun initRecyclerView() {
        citiesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@CitiesActivity)
            this.adapter = this@CitiesActivity.adapter
        }
    }

}
