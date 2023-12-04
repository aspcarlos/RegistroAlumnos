package com.example.registroalumnos

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast

open class ActivityWithMenus : AppCompatActivity() {
    companion object {
        var actividadActual = 0;
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Relacionamos la clase con el layout del menu que hemos creado:
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_principal, menu)

        // Desactivar la opcion de la actividad en la que ya estamos:
        for (i in 0 until  menu.size()) {
            if(i== actividadActual) menu.getItem(i).isEnabled = false
            else menu.getItem(i)?.isEnabled = true
        }

        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.añadir -> {
                actividadActual = 0
                // Hacemos que se abra la pantalla del activity_main:
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent)
                true
            }
            R.id.actualizar -> {
                actividadActual = 1
                // Hacemos que se abra la pantalla del activity_update:
                val intent = Intent(this, UpdateActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent)
                true
            }
            R.id.eliminar -> {
                actividadActual = 2
                // Hacemos que se abra la pantalla del delete_update:
                val intent = Intent(this, DeleteActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent)
                true
            }
            else -> {super.onOptionsItemSelected(item)}
        }
    }

}