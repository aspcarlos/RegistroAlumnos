package com.example.registroalumnos

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.registroalumnos.database.Alumnos
import com.example.registroalumnos.database.AlumnosApp
import com.example.registroalumnos.databinding.ActivityDeleteBinding
import com.example.registroalumnos.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeleteActivity : ActivityWithMenus() {

    private lateinit var listaAlumnos: MutableList<Alumnos>

    // Declaramos el binding
    private lateinit var binding : ActivityDeleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_delete)

        // Inicializamos la lista de alumnos
        listaAlumnos = ArrayList()

        // Evento click del botón eliminar alumno
        binding.bEliminar.setOnClickListener(){

            val nombreAlumno = binding.nombreEliminar.text.toString()

            // Validaciones
            if (nombreAlumno.isEmpty())
            {
                Toast.makeText(this, "No puede haber campos vacíos", Toast.LENGTH_SHORT).show()
            }
            else
            {
                val alumno = Alumnos(nombre = nombreAlumno)

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

    // Funcion para cerrar el teclado
    fun cerrarTeclado() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    // Funcion para limpiar los campos
    fun limpiarCampos() {
        binding.nombreEliminar.text.clear()
    }

}