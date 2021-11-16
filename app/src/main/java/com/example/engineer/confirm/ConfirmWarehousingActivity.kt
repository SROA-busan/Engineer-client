package com.example.engineer.confirm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engineer.MainActivity
import com.example.engineer.R
import com.example.engineer.databinding.ConfirmActivityWarehousingBinding
import com.example.engineer.dto.EngineerBrieflySchedule
import com.example.engineer.dto.ResponseWorkOfdateEngineer
import com.example.engineer.network.RetrofitInstance
import com.example.engineer.schedule.ScheduleAdapter
import com.example.engineer.schedule.ScheduleDetailActivity
import com.example.engineer.sign.SignInActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//입고현황 조회
class ConfirmWarehousingActivity : AppCompatActivity() {
    val dataset = ArrayList<EngineerBrieflySchedule>()

    val view get() = binding!!

    private lateinit var binding: ConfirmActivityWarehousingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ConfirmActivityWarehousingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getWarehousing()
        setRecyclerView()
        setActionBar()

    }

    //툴바 설정
    private fun setActionBar(){
        val toolbar = view.confirmToolBar
        setSupportActionBar(toolbar)
    }



    private fun setRecyclerView() {
        val mRecyclerView = binding.warehousingRecycler

        val intent = Intent(applicationContext, ScheduleDetailActivity::class.java)

        // 어댑터 설정
        val adapter = ScheduleAdapter(dataset)

        adapter.setOnItemClickListener(object : ScheduleAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                // 데이터 전달
                intent.putExtra("brieflySchedule", dataset[position])
                startActivity(intent)
            }
        })
        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }


    //입고현황조회
    private fun getWarehousing() {
        val wareHousing = RetrofitInstance().getData()
        wareHousing.getWarehousingProduct(SignInActivity.engineerId).enqueue(
            object : Callback<List<ResponseWorkOfdateEngineer>> {
                override fun onResponse(
                    call: Call<List<ResponseWorkOfdateEngineer>>,
                    response: Response<List<ResponseWorkOfdateEngineer>>
                ) {
                    Log.d("입고 현황조회 : ", response.body().toString())


                    // 데이터가 있을때
                    if (response.body() != null) {
                        // 데이터 추가
                        response.body()!!.forEach {
                            it.apply {
                                // state가 2,3,4 일때
                                if (status != 0 && status != 1 && status != 5) {
                                    dataset.add(
                                        EngineerBrieflySchedule(
                                            scheduleNum,
                                            startTime.substring(0,10)+"  "+startTime.substring(12,16),
                                            productName,
                                            status
                                        )
                                    )
                                }
                            }
                        }
                        setRecyclerView()

                    }
                }

                override fun onFailure(call: Call<List<ResponseWorkOfdateEngineer>>, t: Throwable) {
                    Log.d("상태 : ", "콜백 실패")
                }
            }
        )
    }
}