package com.example.registroalumnos.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Alumnos")
data class Alumnos(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var nombre:String = "",
    var apellidos:String = "",
    var curso:String = "",
)