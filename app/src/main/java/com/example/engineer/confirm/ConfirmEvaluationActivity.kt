package com.example.engineer.confirm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engineer.databinding.ConfirmActivityEvaluationBinding
import com.example.engineer.dto.EngineerBrieflySchedule
import com.example.engineer.dto.EvaluationData
import com.example.engineer.network.RetrofitInstance
import com.example.engineer.schedule.ScheduleAdapter
import com.example.engineer.schedule.ScheduleDetailActivity
import com.example.engineer.sign.SignInActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//고객 평가 조회
class ConfirmEvaluationActivity : AppCompatActivity() {
    val dataset = ArrayList<EvaluationData>()


    private lateinit var binding: ConfirmActivityEvaluationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ConfirmActivityEvaluationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecyclerView()
        //고객 평가조회
        getEvaluation()
    }

    private fun setRecyclerView() {
        val mRecyclerView = binding.evaluationRecycler

        val intent = Intent(applicationContext, ConfirmEvaluationActivity::class.java)

        // 어댑터 설정
        val adapter = ConfirmEvaluationAdapter(dataset)

        adapter.setOnItemClickListener(object : ConfirmEvaluationAdapter.OnItemClickListener {
            override fun onItemclick(view: View, position: Int) {
                // 데이터 전달
                intent.putExtra("confirmEvaluation", dataset[position])
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
            object : Callback<List<EvaluationData>> {
                override fun onResponse(call: Call<List<EvaluationData>>, response: Response<List<EvaluationData>>) {
                    //TODO 통신은 됬(x)됐(o)지만 서버와 데이터를 맞출 필요가 있음
                    if (response.body() != null) {
                        response.body()!!.forEach {
                            // 데이터 추가
                            response.body()!!.forEach {
                                it.apply {
                                    dataset.add(
                                        EvaluationData(
                                            evaluationNum,
                                            writeDate,
                                            content,
                                            score
                                        )
                                    )
                                }
                            }
                        }
                        setRecyclerView()
                    }
                }

                override fun onFailure(call: Call<List<EvaluationData>>, t: Throwable) {
                    Log.d("상태 : ", "통신실패 // ${t}")
                }
            }
        )
    }
}