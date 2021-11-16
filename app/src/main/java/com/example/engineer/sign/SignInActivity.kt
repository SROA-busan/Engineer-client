package com.example.engineer.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.engineer.MainActivity
import com.example.engineer.databinding.SignInActivityBinding
import com.example.engineer.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    companion object {
        //엔지니어 아이디
        var engineerId = ""
    }

    private lateinit var binding: SignInActivityBinding
    private val view get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignInActivityBinding.inflate(layoutInflater)
        setContentView(view.root)


        binding.signInButton.setOnClickListener {
            engineerId = binding.engineerId.text.toString()
            //통신 성공 [박상환 - 11.08]
            signIn(engineerId, binding.password.text.toString())
//            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    // 로그인
    fun signIn(id: String, pw: String) {
        // API 경로 인터페이스로 레트로펫 인스턴스 생성
        val service = RetrofitInstance().getSignInInstance()
        // api 호출
        service.login(id, pw).enqueue(object : Callback<Int> {
            // 서버 로그인 리턴
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                // 성공 0
                if (response.body() == 0) {
                    Toast.makeText(applicationContext, "로그인 완료", Toast.LENGTH_SHORT).show();
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                }
                // 존재하지 않는 아이디
                else if (response.body() == 1)
                    Toast.makeText(applicationContext, "존재하지 않는 아이디", Toast.LENGTH_SHORT).show();
                // 비밀번호 틀림 2
                else if (response.body() == 2)
                    Toast.makeText(applicationContext, "비밀번호 틀림", Toast.LENGTH_SHORT).show();
                // 최초 로그인시 3
                else if (response.body() == 3) {
                    Toast.makeText(applicationContext, "최초 로그인 입니다. 비밀번호를 변경해주세요", Toast.LENGTH_SHORT).show();
                    val intent = Intent(applicationContext, SettingPasswordActivity::class.java)
                    startActivity(intent)
                }
                else
                    Log.d("상태: ", "response.body() = " + response.body())
            }

            // 실패시
            override fun onFailure(call: Call<Int>, t: Throwable) {
                Log.d("상태: ", "콜백 실패\n이유: ${t}")
                Toast.makeText(applicationContext, "실패", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
