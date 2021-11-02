package com.example.engineer.schedule

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engineer.MainActivity
import com.example.engineer.databinding.NavFragmentScheduleBinding
import com.example.engineer.dto.ScheduleData

class ScheduleFragment : Fragment(){
    private var _binding: NavFragmentScheduleBinding? = null
    private val binding get() = _binding!!

    companion object{
        val dataset = ArrayList<ScheduleData>()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        _binding = NavFragmentScheduleBinding.inflate(inflater,container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle("일정")
        // recyclerView 설정
//        setRecyclerView()
    }

    //툴바 타이틀 설정
    fun setTitle(title: String){
        val mMainactivity = activity as MainActivity
        mMainactivity.setTitle(title)
    }

    //recyclerView 호출
//    fun setRecyclerView() {
//        val mRecyclerView = binding.scheduleRecycler
//        val mSchedule = ScheduleData()
//
//        dataset.add(mSchedule)
//
//        val intent = Intent(applicationContext,)
//        // 어댑터 설정
//        mRecyclerView.adapter = ScheduleAdapter(dataset)
//        // layoutManager 설정
//        mRecyclerView.layoutManager = LinearLayoutManager(context)
//    }



}