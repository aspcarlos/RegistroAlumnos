package com.example.registroalumnos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {

        setTitle("Alumnos_app")

        super.onCreate(savedInstanceState)

        val binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializamos la lista de alumnos
        listaAlumnos = ArrayList()

        binding.bAnadir.setOnClickListener {
            // Obtenemos los datos del alumno
            var nombreAlumno = binding.Nombre.text.toString()
            var apellidosAlumno = binding.Apellidos.text.toString()
            var curso = binding.Curso.text.toString()

            // Validaciones

            if (nombreAlumno.isEmpty() || apellidosAlumno.isEmpty() || curso.isEmpty()) {
                Toast.makeText(this, "No puede haber campos vacíos", Toast.LENGTH_SHORT).show()
            } else {

                // Creamos el alumno
                var alumno = Alumnos(nombre = nombreAlumno, apellidos = apellidosAlumno, curso = curso)

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
            listaAlumnos = AlumnosApp.database.interfazDao().getAllAlumnos()
        }
    }





}