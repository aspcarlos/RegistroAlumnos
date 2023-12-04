package com.example.registroalumnos.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Alumnos::class), version = 1, exportSchema = false)
abstract class DBAlumnos : RoomDatabase() {
    abstract fun interfazDao():InterfazDao
}
