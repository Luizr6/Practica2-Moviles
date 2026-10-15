package com.tecsup.practica2

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NoticiasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noticias)

        val btnPerfil = findViewById<ImageButton>(R.id.btnPerfilHeader)
        val fabCompartir = findViewById<FloatingActionButton>(R.id.fabCompartir)

        // Intent explícito: Noticias -> Perfil
        btnPerfil.setOnClickListener {
            startActivity(Intent(this, PerfilActivity::class.java))
        }

        // Intent implícito: compartir texto con otras apps
        fabCompartir.setOnClickListener {
            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, getString(R.string.share_texto))
            }
            try {
                startActivity(Intent.createChooser(sendIntent, getString(R.string.share_chooser)))
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, R.string.error_sin_app, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
