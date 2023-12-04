package com.example.registroalumnos

import android.os.Bundle
import android.widget.Toast
import com.example.registroalumnos.database.Alumnos
import com.example.registroalumnos.database.AlumnosApp
import com.example.registroalumnos.databinding.ActivityMainBinding
import com.example.registroalumnos.databinding.ActivityUpdateBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateActivity : ActivityWithMenus() {

    private lateinit var listaAlumnos: MutableList<Alumnos>

    // Declaramos el binding
    private lateinit var binding : ActivityUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_update)

        // Inicializamos la lista de alumnos
        listaAlumnos = ArrayList()

        // Evento click del botón actualizar alumno
        binding.bActualizar.setOnClickListener(){

            val nombreAlumno = binding.NombreActualizar.text.toString()
            val cursoAlumno = binding.CursoActualizar.text.toString()

            // Validaciones
            if (nombreAlumno.isEmpty() || cursoAlumno.isEmpty())
            {
                Toast.makeText(this, "No puede haber campos vacíos", Toast.LENGTH_SHORT).show()
            }
            else
            {
                val alumno = Alumnos(nombre = nombreAlumno, curso = cursoAlumno)

                actualizarAlumno(alumno)
                Toast.makeText(this, "Alumno actualizado", Toast.LENGTH_SHORT).show()

            }
        }
    }

    fun actualizarAlumno(nombreAlumno: Alumnos) {
        CoroutineScope(Dispatchers.IO).launch {
            AlumnosApp.database.interfazDao().updateAlumnos(nombreAlumno.nombre, nombreAlumno.curso)
        }
    }

    // Funcion para cerrar el teclado
    fun cerrarTeclado() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    // Funcion para limpiar los campos de texto
    fun limpiarCampos(){
        binding.NombreActualizar.text.clear()
        binding.CursoActualizar.text.clear()
    }

}