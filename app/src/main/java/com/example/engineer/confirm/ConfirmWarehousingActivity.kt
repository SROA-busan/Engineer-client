package com.example.engineer.confirm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.engineer.databinding.ConfirmActivityWarehousingBinding
import com.example.engineer.dto.EvaluationData
import com.example.engineer.dto.ScheduleData

class ConfirmWarehousingActivity : AppCompatActivity() {
    companion object{
        val dataset = ArrayList<ScheduleData>()
    }
    private lateinit var binding: ConfirmActivityWarehousingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ConfirmActivityWarehousingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecycler()
    }

    private fun setRecycler(){
        val mRecyclerView = binding.warehousingRecycler
        val mWarehousingData = ScheduleData()

        dataset.add(mWarehousingData)



    }
}