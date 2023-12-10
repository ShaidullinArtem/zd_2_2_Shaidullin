package com.example.zd_2_2_shaidullin.presentation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import api.auth.AuthAPI
import com.example.zd_2_2_shaidullin.R

class LoginActivity : ComponentActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var api = AuthAPI(this)

        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        val signInButton: Button = findViewById(R.id.signInButton)

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        signInButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()


            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                val response = api.login(username, password)
                if (response.auth_token.isNullOrEmpty()) {
                    Toast.makeText(this, "Не удалось войти в аккаунт", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("token", response.auth_token)
                editor.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }
}