package com.example.engineer.confirm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engineer.databinding.ConfirmActivityEvaluationBinding
import com.example.engineer.dto.EvaluationData
import com.example.engineer.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//고객 평가 조회
class ConfirmEvaluationActivity : AppCompatActivity() {
    companion object {
        val dataset = ArrayList<EvaluationData>()
    }

    private lateinit var binding: ConfirmActivityEvaluationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ConfirmActivityEvaluationBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setRecycler()
        //고객 평가조회
        getEvaluation()
    }

//    private fun setRecycler() {
//        val mRecyclerView = binding.evaluationRecycler
//        val mEvaluationData = EvaluationData()
//
//        dataset.add(mEvaluationData)
//
//        val intent = Intent(applicationContext, ConfirmEvaluationDetailActivity::class.java)
//        val adapter = ConfirmEvaluationAdapter(dataset)
//
//        adapter.setOnItemClickListener(object: ConfirmEvaluationAdapter.onItemClickListener{
//            override fun onItemclick(view: View, position: Int) {
//                intent.putExtra("evaluationData", dataset.get(position))
//                startActivity(intent)
//            }
//        })
//        mRecyclerView.adapter = adapter
//        mRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
//
//    }
    
    //고객 평가조회
    private fun getEvaluation(){
        val evaluationService = RetrofitInstance().getEvalutationInstance()
        evaluationService.askEvalution(6L).enqueue(
            object : Callback<List<EvaluationData>>{
                override fun onResponse(call: Call<List<EvaluationData>>, response: Response<List<EvaluationData>>) {
                    //TODO 통신은 됬지만 서버와 데이터를 맞출 필요가 있음
                    Log.d("고객평가조회 : ", response.body().toString())
                }

                override fun onFailure(call: Call<List<EvaluationData>>, t: Throwable) {
                    Log.d("상태 : ", "통신실패 // ${t}")
                }
            }
        )
    }
}