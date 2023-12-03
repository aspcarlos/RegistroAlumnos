package com.example.registroalumnos

import android.os.Bundle
import android.widget.Toast
import com.example.registroalumnos.database.Alumnos
import com.example.registroalumnos.database.AlumnosApp
import com.example.registroalumnos.databinding.ActivityUpdateBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateActivity : ActivityWithMenus() {

    private lateinit var listaAlumnos: MutableList<Alumnos>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_update)

        // Inicializamos la lista de alumnos
        listaAlumnos = ArrayList()

        // Evento click del botón actualizar alumno
        binding.bActualizar.setOnClickListener(){

            var nombreAlumno = binding.NombreActualizar.text.toString()
            var cursoAlumno = binding.CursoActualizar.text.toString()

            // Validaciones
            if (nombreAlumno.isEmpty() || cursoAlumno.isEmpty())
            {
                Toast.makeText(this, "No puede haber campos vacíos", Toast.LENGTH_SHORT).show()
            }
            else
            {
                var alumno = Alumnos(nombre = nombreAlumno, curso = cursoAlumno)

                actualizarAlumno(alumno)
                Toast.makeText(this, "Alumno actualizado", Toast.LENGTH_SHORT).show()

            }
        }
    }

    fun actualizarAlumno(alumno: Alumnos) {
        CoroutineScope(Dispatchers.IO).launch {
            AlumnosApp.database.interfazDao().updateAlumnos(alumno.nombre, alumno.curso)
        }
    }

}