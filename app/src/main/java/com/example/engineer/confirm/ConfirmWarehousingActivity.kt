package com.example.engineer.confirm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.engineer.databinding.ConfirmActivityWarehousingBinding
import com.example.engineer.dto.EvaluationData

class ConfirmWarehousingActivity : AppCompatActivity() {
    companion object{
        val dataset = ArrayList<EvaluationData>()
    }
    private lateinit var binding: ConfirmActivityWarehousingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ConfirmActivityWarehousingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecycler()
    }

    private fun setRecycler(){

    }
}