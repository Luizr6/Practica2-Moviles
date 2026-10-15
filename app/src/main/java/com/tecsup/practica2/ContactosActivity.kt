package com.tecsup.practica2

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

class ContactosActivity : AppCompatActivity() {

    private lateinit var listContactos: ListView
    private lateinit var contactos: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contactos)

        setSupportActionBar(findViewById<MaterialToolbar>(R.id.toolbarContactos))
        supportActionBar?.apply {
            title = getString(R.string.titulo_contactos)
            setDisplayHomeAsUpEnabled(true)
        }

        contactos = resources.getStringArray(R.array.contactos)
        listContactos = findViewById(R.id.listContactos)
        listContactos.adapter = ArrayAdapter(this, R.layout.item_contacto, contactos)

        // Registra la lista para el menú contextual (se abre con Long Click)
        registerForContextMenu(listContactos)
    }

    // ----- Menú contextual -----
    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if (v.id == R.id.listContactos) {
            menu.setHeaderTitle(R.string.ctx_titulo)
            menuInflater.inflate(R.menu.context_menu, menu)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as? AdapterView.AdapterContextMenuInfo
            ?: return super.onContextItemSelected(item)
        val numero = extraerNumero(contactos[info.position])

        return when (item.itemId) {
            R.id.ctx_llamar -> {
                // Intent implícito: abre el marcador con el número
                abrir(Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", numero, null)))
                true
            }
            R.id.ctx_enviar_mensaje -> {
                // Intent implícito: abre la app de mensajes (SMS)
                val sms = Intent(Intent.ACTION_SENDTO, Uri.fromParts("smsto", numero, null))
                    .putExtra("sms_body", getString(R.string.sms_cuerpo))
                abrir(sms)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    /** "Prensa Editorial: +51 1 987987987" -> "+511987987987" */
    private fun extraerNumero(contacto: String): String =
        contacto.substringAfter(":").filter { it.isDigit() || it == '+' }

    private fun abrir(intent: Intent) {
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, R.string.error_sin_app, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
