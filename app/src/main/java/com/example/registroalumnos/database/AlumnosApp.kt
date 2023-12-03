package com.example.registroalumnos.database

import android.app.Application
import androidx.room.Room

class AlumnosApp : Application() {
    companion object {
        lateinit var database: DBAlumnos
    }

    override fun onCreate() {
        super.onCreate()
        AlumnosApp.database= Room.databaseBuilder(this, DBAlumnos::class.java, "DBAlumnos").build()
    }
}