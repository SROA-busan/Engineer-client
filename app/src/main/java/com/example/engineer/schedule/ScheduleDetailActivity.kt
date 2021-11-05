package com.example.engineer.schedule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engineer.MainActivity
import com.example.engineer.R
import com.example.engineer.databinding.ScheduleActivityDetailBinding
import com.example.engineer.dto.ScheduleData

class ScheduleDetailActivity : AppCompatActivity() {
    // intent 넘어온 값 데이터 바인딩 하기
    private lateinit var binding: ScheduleActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ScheduleActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 인텐트 호출
        val intent = getIntent()
        var pageName = "mainF"
        if(intent.hasExtra("pageName")) {
            pageName = intent.getStringExtra("pageName")!!
        }
        var scheduleData = intent.getSerializableExtra("scheduleData") as ScheduleData

        binding.scheduleData = scheduleData


        binding.scheduleDetailCheckboxGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.schedule_detail_ready -> {
//                    Toast.makeText(this, "방문예정", Toast.LENGTH_SHORT).show()
                    scheduleData.process = "방문예정"
                }
                R.id.schedule_detail_start -> {
                    scheduleData.process = "진행중"
                }
                R.id.schedule_detail_warerhousing -> {
                    scheduleData.process = "입고"
                }
                R.id.schedule_detail_complete -> {
                    scheduleData.process = "수리완료"
                }
            }
        }
        setButtonEvent(pageName)
    }

    private fun setButtonEvent(pageName: String) {
        val intent = Intent(applicationContext, MainActivity::class.java)

        binding.scheduleDetailOkbutton.setOnClickListener {
            intent.putExtra("pageName", pageName)
//            Log.e("detail : position", position.toString())
            startActivity(intent)

        }
    }
}