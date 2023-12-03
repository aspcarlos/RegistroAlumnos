package com.example.registroalumnos

import android.os.Bundle
import android.widget.Toast
import com.example.registroalumnos.database.Alumnos
import com.example.registroalumnos.database.AlumnosApp
import com.example.registroalumnos.databinding.ActivityDeleteBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeleteActivity : ActivityWithMenus() {

    private lateinit var listaAlumnos: MutableList<Alumnos>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_delete)

        // Inicializamos la lista de alumnos
        listaAlumnos = ArrayList()

        // Evento click del botón eliminar alumno
        binding.bEliminar.setOnClickListener(){

            var nombreAlumno = binding.nombreEliminar.text.toString()


            // Validaciones
            if (nombreAlumno.isEmpty())
            {
                Toast.makeText(this, "No puede haber campos vacíos", Toast.LENGTH_SHORT).show()
            }
            else
            {
                var alumno = Alumnos(nombre = nombreAlumno)

                eliminarAlumno(alumno)
                Toast.makeText(this, "Alumno eliminado", Toast.LENGTH_SHORT).show()

            }
        }

    }

    fun eliminarAlumno(alumno: Alumnos){
        CoroutineScope(Dispatchers.IO).launch {
            AlumnosApp.database.interfazDao().deleteAlumnos(alumno.nombre)
        }
    }

}