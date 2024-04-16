package com.example.codelandapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged
import com.example.codelandapp.R
import com.example.codelandapp.databinding.ActivityLoginScreenBinding

class LoginScreenActivity : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        loginBinding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loginBinding.btnLogin.setOnClickListener {
            val userName = loginBinding.tietUsername.text.toString()
            val password = loginBinding.tietPassword.text.toString()

            if (userName.isEmpty()) {
                loginBinding.tilUsername.error = "Required"
                loginBinding.tietUsername.doOnTextChanged { text, start, before, count ->
                    if (text.isNullOrEmpty()) {
                        loginBinding.tilUsername.error = "Required"
                    } else {
                        loginBinding.tilUsername.error = null
                    }
                }
            } else if (password.isEmpty()) {
                loginBinding.tilPassword.error = "Required"
                loginBinding.tietPassword.doOnTextChanged { text, start, before, count ->
                    if (text.isNullOrEmpty()) {
                        loginBinding.tilPassword.error = "Required"
                    } else {
                        loginBinding.tilPassword.error = null
                    }
                }
            } else {
                val intent = Intent(this, ImageUploadActivity::class.java)
                startActivity(intent)
            }


        }
    }
}