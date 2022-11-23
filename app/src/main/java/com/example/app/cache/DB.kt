package com.example.app.cache

import android.content.Context
import android.provider.ContactsContract.Data
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities =  [DataBase::class], version = 1)//, exportSchema = false
abstract  class DB : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
    //вміст

    companion object{
        fun getDB(context: Context): DB{
            return Room.databaseBuilder(context.applicationContext, DB:: class.java, "data_table_DB").build()
        }
    }
}