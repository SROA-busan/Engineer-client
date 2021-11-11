package com.example.engineer.schedule

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engineer.MainActivity
import com.example.engineer.R
import com.example.engineer.databinding.NavFragmentScheduleBinding
import com.example.engineer.dto.EngineerBrieflySchedule
import com.example.engineer.dto.ResponseWorkCntOfMonthEngineer
import com.example.engineer.dto.ResponseWorkOfdateEngineer
import com.example.engineer.dto.ScheduleData
import com.example.engineer.network.RetrofitInstance
import com.example.engineer.sign.SignInActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat


class ScheduleFragment : Fragment() {
    companion object {
        val dataset = ArrayList<EngineerBrieflySchedule>()
    }

    private var _binding: NavFragmentScheduleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NavFragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle("일정")
        // recyclerView 설정
        setRecyclerView()

        setCalendar()
    }

    //툴바 타이틀 설정
    fun setTitle(title: String) {
        val mMainactivity = activity as MainActivity
        mMainactivity.setTitle(title)

    }

    // 서버에서 데이터 리스트 받아오기
    fun setCalendar() {
        //날짜 클릭 이벤트
        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            //날짜 변경시 데이터셋 클리어
            dataset.clear()
            //월, 일
            var vMonth = month.toString()
            var vDay = dayOfMonth.toString()

            //포맷 맞춤
            if(month < 10)
                vMonth = "0${month}"
            if(dayOfMonth < 10)
                vDay = "0${dayOfMonth}"
            
            //날짜 조합
            val date = "${year}-${vMonth}-${vDay}"
            //일간 일정 조회
            Log.d("날짜 ", date.toString())
            workOfdate(date)
            val date2 = "${year}-${vMonth}"
            workOfMonth(date2)
        }
    }

    //recyclerView 호출
    fun setRecyclerView() {
        val mRecyclerView = binding.scheduleRecycler

        val intent = Intent(context, ScheduleDetailActivity::class.java)

        // 어댑터 설정
        val adapter = ScheduleAdapter(dataset)
        // layoutManager 설정

        adapter.setOnItemClickListener(object : ScheduleAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                //데이터 전달
                intent.putExtra("brieflySchedule", dataset[position])
                startActivity(intent)
            }
        })
        mRecyclerView.adapter = adapter
    }

    //월별 일정 조회
    fun workOfMonth(month: String){
        val workOfMonthService = RetrofitInstance().getData()
        workOfMonthService.inqueryWorkCntOfMonth(SignInActivity.engineerId, month).enqueue(object: Callback<List<ResponseWorkCntOfMonthEngineer>>{
            override fun onResponse(
                call: Call<List<ResponseWorkCntOfMonthEngineer>>,
                response: Response<List<ResponseWorkCntOfMonthEngineer>>
            ) {
                Log.d("월별 일정조회", response.body().toString())
            }

            override fun onFailure(call: Call<List<ResponseWorkCntOfMonthEngineer>>, t: Throwable) {
                Log.e("통신 실패 ", t.toString())
            }
        })
    }
    //일간 일정 조회
    fun workOfdate(date: String){
        val workOfDateService = RetrofitInstance().getData()
        workOfDateService.inqueryWorkOfDate(SignInActivity.engineerId, date).enqueue(object : Callback<List<ResponseWorkOfdateEngineer>>{
            override fun onResponse(
                call: Call<List<ResponseWorkOfdateEngineer>>,
                response: Response<List<ResponseWorkOfdateEngineer>>
            ) {
                Log.d("일간 일정조회", response.body().toString())
                if(response.body() != null) {
                    response.body()!!.forEach {
                        it.apply {
                            dataset.add(EngineerBrieflySchedule(
                                scheduleNum,
                                startTime,
                                productName,
                                status)
                            )
                        }
                    }
                    setRecyclerView()
                }
            }

            override fun onFailure(call: Call<List<ResponseWorkOfdateEngineer>>, t: Throwable) {
                Log.e("통신 실패 ", t.toString())
            }
        })
    }
}