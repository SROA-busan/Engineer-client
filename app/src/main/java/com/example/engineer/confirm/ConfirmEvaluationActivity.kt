package com.example.engineer.confirm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.engineer.databinding.ConfirmActivityEvaluationBinding
import com.example.engineer.dto.ConfirmData

class ConfirmEvaluationActivity : AppCompatActivity() {
    companion object {
        val dataset = ArrayList<ConfirmData>()
    }

    private lateinit var binding: ConfirmActivityEvaluationBinding

    override fun oncreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ConfirmActivityEvaluationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecycler()
    }

    private fun setRecycler() {
        val mRecyclerView = binding.confirmRecyclerEvaluation

        val mConfirmData = ConfirmData(
            dateTime = "0",
            product = "0",
            grade = "000",
            process = "0000?",
            content = "00000000000"
        )
        dataset.add(mConfirmData)

        val intent = Intent(applicationContext, ConfirmEvaluationActivity::class.java)
    }
}