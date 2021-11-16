package com.example.engineer.confirm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engineer.databinding.ConfirmActivityEvaluationBinding
import com.example.engineer.dto.EngineerBrieflySchedule
import com.example.engineer.dto.ResponseEngineerEvaluation
import com.example.engineer.network.RetrofitInstance
import com.example.engineer.schedule.ScheduleAdapter
import com.example.engineer.schedule.ScheduleDetailActivity
import com.example.engineer.sign.SignInActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//고객 평가 조회
class ConfirmEvaluationActivity : AppCompatActivity() {
    val dataset = ArrayList<ResponseEngineerEvaluation>()

    val view get() = binding!!


    private lateinit var binding: ConfirmActivityEvaluationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ConfirmActivityEvaluationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecyclerView()
        //고객 평가조회
        getEvaluation()
    }

    //툴바 설정
    private fun setActionBar(){
        val toolbar = view.confirmToolBar
        setSupportActionBar(toolbar)
    }

    private fun setRecyclerView() {
        val mRecyclerView = binding.evaluationRecycler

        val intent = Intent(applicationContext, ConfirmEvaluationDetailActivity::class.java)

        // 어댑터 설정
        val adapter = ConfirmEvaluationAdapter(dataset)

        // 아이템 클릭 이벤트 설정
        adapter.setOnItemClickListener(object : ConfirmEvaluationAdapter.OnItemClickListener {
            override fun onItemclick(view: View, position: Int) {
                // 데이터 전달
                intent.putExtra("evaluationData", dataset[position])
                Log.d("제대로 가는지", dataset[position].toString())
                startActivity(intent)
            }
        })

        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }

    //고객 평가조회
    private fun getEvaluation() {
        val evaluationService = RetrofitInstance().getEvalutationInstance()
        evaluationService.inqueryEvalution(SignInActivity.engineerId).enqueue(
            object : Callback<List<ResponseEngineerEvaluation>> {
                override fun onResponse(call: Call<List<ResponseEngineerEvaluation>>, response: Response<List<ResponseEngineerEvaluation>>) {
                    Log.d("평가", response.body().toString())
                    if (response.body() != null) {
                        // 데이터 추가
                        response.body()!!.forEach {
                            it.apply {
                                Log.d("response.body)(", it.toString())
                                dataset.add(
                                    ResponseEngineerEvaluation(
                                        classifyName,
                                        writeDate,
                                        content,
                                        score
                                    )
                                )
                            }

                        }
                        setRecyclerView()
                    }
                }

                override fun onFailure(call: Call<List<ResponseEngineerEvaluation>>, t: Throwable) {
                    Log.d("상태 : ", "통신실패 // ${t}")
                }
            }
        )
    }
}