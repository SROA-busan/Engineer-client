package com.example.engineer.confirm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.engineer.R
import com.example.engineer.databinding.ConfirmEvaluationActivityDetailBinding
import com.example.engineer.dto.ResponseEngineerEvaluation
import com.example.engineer.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConfirmEvaluationDetailActivity : AppCompatActivity() {

    private lateinit var binding: ConfirmEvaluationActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ConfirmEvaluationActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 인텐트 호출
        val intent = getIntent()
        var evaluationData = intent.getSerializableExtra("evaluationData") as ResponseEngineerEvaluation
        Log.d("detail화면", evaluationData.toString())
        val score = findViewById<TextView>(R.id.detail_score)

        score.setText(evaluationData.score.toString())
        binding.evaluationData = evaluationData

    }
}