package com.example.engineer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engineer.databinding.NavFragmentMainBinding
import com.example.engineer.dto.EngineerBrieflySchedule
import com.example.engineer.dto.ResponseLoginEngineer
import com.example.engineer.network.RetrofitInstance
import com.example.engineer.schedule.ScheduleAdapter
import com.example.engineer.schedule.ScheduleDetailActivity
import com.example.engineer.sign.SignInActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment() {
    val dataset = ArrayList<EngineerBrieflySchedule>()

    private var _binding: NavFragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NavFragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataset.clear()
        //툴바 타이틀 설정
        setTitle()
        //엔지니어 일정 로드
        getEngineerBrieflySchedule()


    }

    //툴바 타이틀 설정
    private fun setTitle() {
        val mMainactivity = activity as MainActivity
        mMainactivity.setTitle("홈")
    }

    //일정 리스트
    fun setRecyclerView() {
        val mRecyclerView = binding.mainRecycler

        val intent = Intent(context, ScheduleDetailActivity::class.java)

        // 어댑터 설정
        val adapter = ScheduleAdapter(dataset)
        // layoutManager 설정

        adapter.setOnItemClickListener(object : ScheduleAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                //데이터 전달
                intent.putExtra("brieflySchedule", dataset[position])
                //화면 이동
                startActivity(intent)
            }
        })
        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    //엔지니어 오늘 일정 조회
    private fun getEngineerBrieflySchedule() {
        val brieflyScheduleService = RetrofitInstance().getData()
        brieflyScheduleService.engineeerMainPage(SignInActivity.engineerId)
            .enqueue(object : Callback<ResponseLoginEngineer> {
                override fun onResponse(call: Call<ResponseLoginEngineer>, response: Response<ResponseLoginEngineer>) {
                    Log.d("상태 : ", response.body().toString())
                    //데이터가 있을 시
                    var temp = arrayOfNulls<EngineerBrieflySchedule>(response.body()!!.list.size)
                    var temp2 = arrayOfNulls<EngineerBrieflySchedule>(response.body()!!.list.size)
//                    response.body()!!.list.forEachIndexed { index, it ->
//                        temp[index] = it.scheduleNum
//                    }

                    if (response.body() != null) {

                            response.body()!!.list.forEachIndexed {index,it->
                                // 확인
                                temp2[index]=it
                                var cnt = index
                                Log.d("사이즈", cnt.toString())

                                if(index==0){
                                    temp[index]=it
                                    dataset.add(it)
                                }else{
                                    for(i:Int in 0..cnt-1){
                                        if(temp2[i]?.scheduleNum==it.scheduleNum){
                                            break
                                        }
                                        if(index>0&&temp2[i]?.scheduleNum!=it.scheduleNum){
                                            dataset.add(it)
                                            break
                                        }

                                    }
                                }


                                Log.d("데이터셋", dataset.size.toString())

//                                else if(index>0 && it.scheduleNum!=temp[index-1]!!.scheduleNum){
//                                    for(i : Int in 0..cnt){
//                                        if(temp2[i]!!.scheduleNum==it.scheduleNum){
//
//                                        }
//                                    }
//                                    temp[index]=it
//                                    dataset.add(it)
//                                }


                                Log.d("데이터 확인", dataset.toString())
                            }


                    }
                    //리사이클러뷰 설정
                    setRecyclerView()
                    binding.engineerName.text = response.body()!!.name
                    binding.serviceCenter.text = response.body()!!.centerName
                    binding.engineerAvg.text = response.body()!!.avgScore.toString()

                }


                override fun onFailure(call: Call<ResponseLoginEngineer>, t: Throwable) {
                    Log.e("통신 실패 : ", t.toString())
                }
            })
    }


}