package com.tecsup.practica2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

class PerfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        // La Toolbar actúa como ActionBar para mostrar el menú de opciones
        setSupportActionBar(findViewById<MaterialToolbar>(R.id.toolbarPerfil))
        supportActionBar?.apply {
            title = getString(R.string.titulo_perfil)
            setDisplayHomeAsUpEnabled(true)
        }

        // Intent explícito: Perfil -> Login (limpia la pila de pantallas)
        findViewById<Button>(R.id.btnCerrarSesion).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
            finish()
        }
    }

    // ----- Menú de opciones -----
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_privacidad -> {
                startActivity(Intent(this, PrivacidadActivity::class.java))
                true
            }
            R.id.menu_contactos -> {
                startActivity(Intent(this, ContactosActivity::class.java))
                true
            }
            R.id.menu_central -> {
                Toast.makeText(this, R.string.toast_central, Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Flecha de retorno de la barra superior
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
