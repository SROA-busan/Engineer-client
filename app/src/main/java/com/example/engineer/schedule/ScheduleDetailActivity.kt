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
import com.example.engineer.dto.EngineerBrieflySchedule
import com.example.engineer.dto.EngineerDetailSchedule
import com.example.engineer.dto.ScheduleData
import com.example.engineer.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScheduleDetailActivity : AppCompatActivity() {
    // intent 넘어온 값 데이터 바인딩 하기
    private lateinit var binding: ScheduleActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ScheduleActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        //엔지니어 일정 상세정보
        val brieflySchedule = intent.getSerializableExtra("brieflySchedule") as EngineerBrieflySchedule
        Log.d("brieflySchedule", brieflySchedule.toString())
        //버튼 이벤트 설정
        setButtonEvent(null, brieflySchedule)
        //일정 상세내용
        selectOneSchedule(brieflySchedule.scheduleNum)
    }

    private fun setButtonEvent(pageName: String?, brieflySchedule: EngineerBrieflySchedule) {
        val intent = Intent(applicationContext, MainActivity::class.java)
        //ok버튼 비활성화
        binding.scheduleDetailOkbutton.isEnabled = false
        //OK버튼
        binding.scheduleDetailOkbutton.setOnClickListener {
            intent.putExtra("pageName", pageName)
            startActivity(intent)
        }
        //TODO 버튼 이벤트 수정
        //라디오버튼 이벤트
        binding.scheduleDetailCheckboxGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
//                R.id.schedule_detail_ready -> {
//                    scheduleData.process = "방문예정"
//                }
//                R.id.schedule_detail_start -> {
//                    scheduleData.process = "진행중"
//                }
//                R.id.schedule_detail_warerhousing -> {
//                    scheduleData.process = "입고"
//                }
//                R.id.schedule_detail_complete -> {
//                    scheduleData.process = "수리완료"
//                }
            }
            //ok버튼 활성화
            binding.scheduleDetailOkbutton.isEnabled = true
        }
    }

    //일정 상세내용
    fun selectOneSchedule(scheduelNum: Long) {
        val selectOneSchedule = RetrofitInstance().getData()
        selectOneSchedule.selectOneSchedule(scheduelNum).enqueue(object : Callback<EngineerDetailSchedule> {
            override fun onResponse(call: Call<EngineerDetailSchedule>, response: Response<EngineerDetailSchedule>) {
                Log.d("상태 : ", response.body().toString())
            }

            override fun onFailure(call: Call<EngineerDetailSchedule>, t: Throwable) {
                Log.e("통신 실패 : ", t.toString())
            }
        })
    }
}