package com.example.registroalumnos

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.registroalumnos.database.Alumnos
import com.example.registroalumnos.database.AlumnosApp
import com.example.registroalumnos.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    // Declaramos la lista de alumnos mutable
    lateinit var listaAlumnos: MutableList<Alumnos>

    // Declaramos el binding
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        setTitle("Alumnos_app")

        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializamos la lista de alumnos
        listaAlumnos = ArrayList()

        binding.bAnadir.setOnClickListener {
            // Obtenemos los datos del alumno
            val nombreAlumno = binding.Nombre.text.toString()
            val apellidosAlumno = binding.Apellidos.text.toString()
            val curso = binding.Curso.text.toString()

            // Validaciones

            if (nombreAlumno.isEmpty() || apellidosAlumno.isEmpty() || curso.isEmpty()) {
                Toast.makeText(this, "No puede haber campos vacíos", Toast.LENGTH_SHORT).show()
            } else {

                // Creamos el alumno
                //anadirAlumno(Alumnos(nombre = nombreAlumno, apellidos = apellidosAlumno, curso = curso))
                val alumno = Alumnos(nombre = nombreAlumno, apellidos = apellidosAlumno, curso = curso)

                // Añadimos el alumno a la lista
                listaAlumnos.add(alumno)

                // Añadimos el alumno a la base de datos
                anadirAlumno(alumno)

                // muestro un mensaje de que se ha añadido el alumno
                Toast.makeText(this, "Alumno añadido", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun anadirAlumno(alumno: Alumnos) {
        CoroutineScope(Dispatchers.IO).launch {
            AlumnosApp.database.interfazDao().addAlumnos(alumno)
        }
    }

    // Funcion para cerrar el teclado
    fun cerrarTeclado() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    // Funcion para limpiar los campos
    fun limpiarCampos() {
        binding.Nombre.text.clear()
        binding.Apellidos.text.clear()
        binding.Curso.text.clear()
    }

}