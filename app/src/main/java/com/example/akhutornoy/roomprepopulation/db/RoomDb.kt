package com.example.akhutornoy.roomprepopulation.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.akhutornoy.roomprepopulation.R

@Database(entities = [(City::class)], version = 1, exportSchema = false)
abstract class RoomDb : RoomDatabase() {
    abstract fun cityDao(): CityDao
    companion object {
        @Volatile private var INSTANCE: RoomDb? = null

        fun getInstance(context: Context): RoomDb {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = createInstance(context)
                }
                return INSTANCE!!
            }
        }

        private fun createInstance(context: Context) =
            Room.databaseBuilder(context.applicationContext, RoomDb::class.java, "DataBase.db")
                .allowMainThreadQueries()
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Thread(Runnable { prepopulateDb(context, getInstance(context)) }).start()
                    }
                })
                .fallbackToDestructiveMigration()
                .build()

        private fun prepopulateDb(context: Context, db: RoomDb) {
            val cities = context.resources
                .getStringArray(R.array.largest_cities)
                .map { City(it) }
            db.cityDao().insert(cities)
        }
    }
}