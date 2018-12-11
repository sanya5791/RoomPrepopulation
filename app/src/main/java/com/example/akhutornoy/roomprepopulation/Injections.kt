package com.example.akhutornoy.roomprepopulation

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import com.example.akhutornoy.roomprepopulation.db.Db
import com.example.akhutornoy.roomprepopulation.ui.CitiesViewModel
import com.example.akhutornoy.roomprepopulation.ui.CitiesActivity
import com.example.akhutornoy.roomprepopulation.ui.ViewModelFactory

object Injections {

    fun provideCitiesViewModel(activity: CitiesActivity) =
        ViewModelProviders.of(activity, provideViewModelFactory(activity))
            .get(CitiesViewModel::class.java)

    private fun provideUserDataSource(context: Context)= Db.getInstance(context)

    private fun provideViewModelFactory(context: Context) =
        ViewModelFactory(provideUserDataSource(context))
}