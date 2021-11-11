package com.example.engineer.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.engineer.MainActivity
import com.example.engineer.R
import com.example.engineer.databinding.SettingPasswordActivityBinding
import com.example.engineer.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SettingPasswordActivity : AppCompatActivity() {
    private var _binding: SettingPasswordActivityBinding? = null
    private val binding get() = _binding!!
    private var isChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = SettingPasswordActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.settingPasswordConfirmButton.setOnClickListener { 
            changePassword()
        }
    }
    
    //비밀번호 변경 통신
    fun changePassword() {
        //비밀번호 가져옴
        val pw = binding.editEngineerPasswordConfirm.text.toString()

        //비밀번호 변경 통신
        val service = RetrofitInstance().getSignInInstance()
        service.engineerSettingPw(SignInActivity.engineerId, pw).enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                //메인 화면으로 이동
                startActivity(Intent(applicationContext, MainActivity::class.java))
                Log.d("상태 : ", "통신 성공")
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Log.e("통신 실패", t.toString())
            }
        })
    }
}