package com.example.akhutornoy.roomprepopulation.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.akhutornoy.roomprepopulation.R

@Database(entities = [(City::class)], version = 1, exportSchema = false)
abstract class Db : RoomDatabase() {
    abstract fun cityDao(): CityDao
    companion object {
        @Volatile private var INSTANCE: Db? = null

        fun getInstance(context: Context): Db {
            val thread = Thread.currentThread()
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = createInstance(context)
                }
                return INSTANCE!!
            }
        }

        private fun createInstance(context: Context) =
            Room.databaseBuilder(context.applicationContext, Db::class.java, "DataBase.db")
                .allowMainThreadQueries()
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Thread(Runnable { prepopulateDb(context, getInstance(context)) }).start()
                    }
                })
                .fallbackToDestructiveMigration()
                .build()

        private fun prepopulateDb(context: Context, db: Db) {
            db.cityDao().insert(City(context.getString(R.string.city_1)))
            db.cityDao().insert(City(context.getString(R.string.city_2)))
            db.cityDao().insert(City(context.getString(R.string.city_3)))
        }
    }
}