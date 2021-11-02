package com.example.engineer.schedule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        var scheduleData = intent.getSerializableExtra("scheduleData") as ScheduleData

        binding.scheduleDatta = scheduleData
    }
}