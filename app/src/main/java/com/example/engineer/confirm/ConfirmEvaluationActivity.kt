package com.example.engineer.confirm

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.engineer.databinding.ConfirmActivityEvaluationBinding
import com.example.engineer.dto.EvaluationData

class ConfirmEvaluationActivity : AppCompatActivity() {
    companion object {
        val dataset = ArrayList<EvaluationData>()
    }

    private lateinit var binding: ConfirmActivityEvaluationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ConfirmActivityEvaluationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecycler()
    }

    private fun setRecycler() {
        val mRecyclerView = binding.evaluationRecyclerEvaluation

        val mEvaluationData = EvaluationData()
        dataset.add(mEvaluationData)

        val intent = Intent(applicationContext, ConfirmEvaluationActivity::class.java)
        val adapter = ConfirmEvaluationAdapter(dataset)

        adapter.setOnItemClickListener(object: ConfirmEvaluationAdapter.onItemClickListener{
            override fun onItemclick(view: View, position: Int) {
//                intent.putExtra("confirmData", dataset.get(position))
                startActivity(intent)
            }
        })
    }
}