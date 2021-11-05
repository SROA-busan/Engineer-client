package com.example.engineer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.engineer.databinding.SignInActivityBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: SignInActivityBinding
    private val view get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignInActivityBinding.inflate(layoutInflater)
        setContentView(view.root)

        binding.signInButton.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}