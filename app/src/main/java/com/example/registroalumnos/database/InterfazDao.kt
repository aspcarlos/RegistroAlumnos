package com.example.registroalumnos.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface InterfazDao {

    // Devuelve todos los alumnos de la base de datos
    @Query("SELECT * FROM Alumnos")
    fun getAllAlumnos(): MutableList<Alumnos>

    // Devuelve un alumno de la base de datos
    @Query("SELECT * FROM alumnos WHERE nombre like :nombre")
    fun obteneralumnopornombre(nombre:String): Alumnos

    // Inserta un alumno en la base de datos
    @Insert
    fun addAlumnos(elemento: Alumnos): Long

    // Actualiza el curso de un alumno de la base de datos
    @Update
    fun updateAlumnos(elemento: String, curso: String): Int

    // Elimina un alumno de la base de datos
    @Delete
    fun deleteAlumnos(elemento: String): Int
}