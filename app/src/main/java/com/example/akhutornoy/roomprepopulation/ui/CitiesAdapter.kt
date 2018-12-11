package com.example.akhutornoy.roomprepopulation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.akhutornoy.roomprepopulation.R
import com.example.akhutornoy.roomprepopulation.db.City
import kotlinx.android.synthetic.main.item_city.view.*

class CitiesAdapter: RecyclerView.Adapter <CitiesAdapter.ViewHolder>() {
    private lateinit var cities: List<City>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_city, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (this::cities.isInitialized) cities.size else 0
    }

    fun setCities(cities: List<City>) {
        this.cities = cities
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cities[position])
    }

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(city: City) {
            view.cityTextView.text = city.name
        }
    }
}