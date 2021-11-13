package com.example.engineer.schedule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.engineer.MainActivity
import com.example.engineer.databinding.ScheduleActivityDetailBinding
import com.example.engineer.dto.EngineerBrieflySchedule
import com.example.engineer.dto.EngineerDetailSchedule
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


        // 입고버튼
        warehousingButtonEvent(brieflySchedule)

        // 처리완료 버튼
        repairCompleteButtonEvent(brieflySchedule)
        // 입고 수리완료 버튼
        warehousingRepairCompleteButtonEvent(brieflySchedule)

        //버튼 이벤트 설정
        setButtonEvent()
        //일정 상세내용
        selectOneSchedule(brieflySchedule.scheduleNum)
    }

    // 입고버튼
    private fun warehousingButtonEvent(brieflySchedule: EngineerBrieflySchedule) {
        val intent = Intent(applicationContext, MainActivity::class.java)

        Log.d("d", brieflySchedule.state.toString())
        if (brieflySchedule.state != 0) {
            binding.detailWarehousingButton.visibility = View.GONE
        }
        binding.detailWarehousingButton.setOnClickListener {
            getRequestWarehousing(brieflySchedule.scheduleNum)
            Log.d("버튼이 제대로 눌림?", "dd")
            startActivity(intent)
        }
    }


    // 처리완료 버튼
    private fun repairCompleteButtonEvent(brieflySchedule: EngineerBrieflySchedule) {
        val intent = Intent(applicationContext, MainActivity::class.java)
        if (brieflySchedule.state != 0 && brieflySchedule.state != 4) {
            binding.detailRepairCompleteButton.visibility = View.GONE
        }
        binding.detailRepairCompleteButton.setOnClickListener {
            getRequestComplete(brieflySchedule.scheduleNum)
            startActivity(intent)
        }
    }

    // 입고 수리완료 버튼
    private fun warehousingRepairCompleteButtonEvent(brieflySchedule: EngineerBrieflySchedule) {
        val intent = Intent(applicationContext, MainActivity::class.java)

        if (brieflySchedule.state != 2) {
            binding.detailWarehousingRepairButton.visibility = View.GONE
        }
        binding.detailWarehousingRepairButton.setOnClickListener {
            getRequestRepair(brieflySchedule.scheduleNum)
            startActivity(intent)
        }
    }

    // OK 버튼 (없애도 상관 X)
    private fun setButtonEvent() {
        binding.detailOkbutton.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
    }

    //일정 상세내용
    fun selectOneSchedule(scheduelNum: Long) {
        val selectOneSchedule = RetrofitInstance().getData()
        selectOneSchedule.selectOneSchedule(scheduelNum).enqueue(object : Callback<EngineerDetailSchedule> {
            override fun onResponse(call: Call<EngineerDetailSchedule>, response: Response<EngineerDetailSchedule>) {
                Log.d("상태 : ", response.body().toString())
                binding.detailProduct.text = response.body()?.productName.toString()
                binding.detailContent.text = response.body()?.content.toString()
                binding.detailDataTime.text = response.body()?.startTime.toString()
                binding.detailUserAddressInfo.text = response.body()?.customerAddress.toString()
            }

            override fun onFailure(call: Call<EngineerDetailSchedule>, t: Throwable) {
                Log.e("통신 실패 : ", t.toString())
            }
        })
    }

    // 입고처리
    fun getRequestWarehousing(scheduleNum: Long) {
        val requestWarehousing = RetrofitInstance().getRepairSchdule()
        requestWarehousing.requestWarehousing(scheduleNum).enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                Log.d("입고처리 : ", response.body().toString())
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Log.e("통신 실패 : ", t.toString())
            }
        })
    }

    // 고객대면, 처리완료
    fun getRequestComplete(scheduleNum: Long) {
        val requestComplete = RetrofitInstance().getRepairSchdule()
        requestComplete.requestComplete(scheduleNum).enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                Log.d("고객대면, 처리완료 : ", response.body().toString())
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Log.e("통신 실패 : ", t.toString())
            }
        })
    }

    // 입고중인 장비 수리 완료
    fun getRequestRepair(scheduleNum: Long) {
        val requestRepair = RetrofitInstance().getRepairSchdule()
        requestRepair.requestRepair(scheduleNum).enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                Log.d("입고중인 장비 수리 완료, 처리완료 : ", response.body().toString())
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Log.e("통신 실패 : ", t.toString())
            }
        })
    }
}
