package com.example.app.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="data_table")
data class DataBase(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "city")
    val city: String,
    @ColumnInfo(name = "temp")
    val temp: String,
    @ColumnInfo(name = "stan")
    val stan: String
)
