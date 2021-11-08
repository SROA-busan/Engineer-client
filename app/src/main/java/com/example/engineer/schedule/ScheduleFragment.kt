package com.example.engineer.schedule

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engineer.MainActivity
import com.example.engineer.R
import com.example.engineer.databinding.NavFragmentScheduleBinding
import com.example.engineer.dto.ScheduleData


class ScheduleFragment : Fragment() {
    companion object {
        val dataset = ArrayList<ScheduleData>()
    }

    private var _binding: NavFragmentScheduleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NavFragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle("일정")
        // recyclerView 설정
        setRecyclerView()

    }

    //툴바 타이틀 설정
    fun setTitle(title: String) {
        val mMainactivity = activity as MainActivity
        mMainactivity.setTitle(title)

    }

    // 서버에서 데이터 리스트 받아오기
    fun getSearchData() {}

    //recyclerView 호출
    fun setRecyclerView() {
        val mRecyclerView = binding.scheduleRecycler
        getSearchData()

        val mSchedule = ScheduleData(
            "2021",
            "냉장고",
            "대연동",
            "방문예정",
            "010",
            "안오노"
        )


        dataset.add(mSchedule)

        val intent = Intent(context, ScheduleDetailActivity::class.java)
        intent.putExtra("scheduleData", mSchedule)

        // 어댑터 설정
        val adapter = ScheduleAdapter(dataset)
        // layoutManager 설정

        adapter.setOnItemClickListener(object : ScheduleAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                startActivity(intent)
            }
        })
        mRecyclerView.adapter = adapter


    }
}