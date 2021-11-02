package com.example.engineer.schedule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.engineer.databinding.SearchActivityDetailBinding
import com.example.engineer.dto.ScheduleData

class ScheduleDetailActivity : AppCompatActivity() {
    companion object{
        val dataset = ArrayList<ScheduleData>()
    }
    private lateinit var binding: SearchActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        setRecycler()

//        // 인텐트 호출
//        val intent = getIntent()
//        var scheduleData = ScheduleData()
    }

}