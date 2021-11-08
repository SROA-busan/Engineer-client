package com.example.engineer.sign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import com.example.engineer.R
import com.example.engineer.databinding.SettingPasswordActivityBinding

class SettingPasswordActivity : AppCompatActivity() {
    private  var _binding: SettingPasswordActivityBinding? = null
    private val binding get() = _binding!!
    private var isChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = SettingPasswordActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}