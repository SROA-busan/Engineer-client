package com.example.engineer.confirm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.engineer.databinding.ConfirmActivityWarehousingBinding
import com.example.engineer.databinding.SignInActivityBinding
import com.example.engineer.dto.EvaluationData
import com.example.engineer.dto.ResponseWorkOfdateEngineer
import com.example.engineer.dto.ScheduleData
import com.example.engineer.dto.ScheduleHandling
import com.example.engineer.network.RetrofitInstance
import com.example.engineer.sign.SignInActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//입고현황 조회
class ConfirmWarehousingActivity : AppCompatActivity() {
    companion object{
        val dataset = ArrayList<ScheduleData>()
    }
    private lateinit var binding: ConfirmActivityWarehousingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ConfirmActivityWarehousingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getWarehousing()
//        setRecycler()
    }

    private fun setRecycler(){
        val mRecyclerView = binding.warehousingRecycler
        val mWarehousingData = ScheduleData()

        dataset.add(mWarehousingData)
    }
    
    //입고현황조회
    private fun getWarehousing(){
        val wareHousing = RetrofitInstance().getData()
        wareHousing.getWarehousingProduct(SignInActivity.engineerId).enqueue(
            object: Callback<List<ResponseWorkOfdateEngineer>>{
                override fun onResponse(
                    call: Call<List<ResponseWorkOfdateEngineer>>,
                    response: Response<List<ResponseWorkOfdateEngineer>>
                ) {
                    Log.d("입고 현황조회 : ", response.body().toString())
                }

                override fun onFailure(call: Call<List<ResponseWorkOfdateEngineer>>, t: Throwable) {
                    Log.d("상태 : ", "콜백 실패")
                }
            }
        )
    }
}