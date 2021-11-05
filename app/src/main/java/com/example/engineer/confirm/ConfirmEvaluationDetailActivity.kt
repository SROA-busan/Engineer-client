package com.example.engineer.confirm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.engineer.databinding.ConfirmEvaluationActivityDetailBinding
import com.example.engineer.dto.EvaluationData

class ConfirmEvaluationDetailActivity : AppCompatActivity() {

    private lateinit var binding: ConfirmEvaluationActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ConfirmEvaluationActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 인텐트 호출
        val intent = getIntent()
        var evaluationData = intent.getSerializableExtra("evaluationData") as EvaluationData

        binding.evaluationData = evaluationData
    }
}