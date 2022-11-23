package com.example.app.cache

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {

    @Insert //(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(word: DataBase)

    @Query("SELECT * FROM data_table")
    fun getAll():List<DataBase>

    @Delete
    fun delete(user: DataBase)

}