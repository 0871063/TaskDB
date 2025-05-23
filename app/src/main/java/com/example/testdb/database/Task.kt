package com.example.testdb.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name : String,
    var isDOne : Boolean
)