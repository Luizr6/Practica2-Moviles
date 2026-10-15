package com.tecsup.practica2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        // Login simulado + Intent explícito hacia NoticiasActivity
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                startActivity(Intent(this, NoticiasActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, R.string.login_campos_vacios, Toast.LENGTH_SHORT).show()
            }
        }

        btnRegister.setOnClickListener {
            Toast.makeText(this, R.string.login_registro_proximamente, Toast.LENGTH_SHORT).show()
        }
    }
}
