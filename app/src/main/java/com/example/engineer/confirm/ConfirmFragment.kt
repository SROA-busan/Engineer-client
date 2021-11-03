package com.example.engineer.confirm

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.engineer.MainActivity
import com.example.engineer.R
import com.example.engineer.databinding.ConfirmFragmentMainBinding
import com.example.engineer.dto.ConfirmData

class ConfirmFragment: Fragment() {
    companion object{
        val dataset = ArrayList<ConfirmData>()
    }

    private var _binding: ConfirmFragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        _binding = ConfirmFragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        setTitle("조회")
        // 버튼 이벤트 설정
//        setButtonEvent()
    }

    // 툴바 타이틀 설정
    fun setTitle(title: String){
        val mMainActivity = activity as MainActivity
        mMainActivity.setTitle(title)
    }

//    fun setButtonEvent(){
//        // 입고 현황 조회
//        binding.confirmWarehousing.setOnClickListener {
//            startActivity(Intent(context, ConfirmWarehousingActivity::class.java))
//        }
//        // 고객 평가 조회
//        binding.confirmEvaluation.setOnClickListener {
//            startActivity(Intent(context, ConfirmEvaluationActivity::class.java))
//        }
//    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}