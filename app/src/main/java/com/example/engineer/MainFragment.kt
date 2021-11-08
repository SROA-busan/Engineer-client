package com.example.engineer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engineer.databinding.NavFragmentMainBinding
import com.example.engineer.dto.ScheduleData
import com.example.engineer.schedule.ScheduleAdapter
import com.example.engineer.schedule.ScheduleDetailActivity
import com.example.engineer.schedule.ScheduleFragment

class MainFragment : Fragment() {
    companion object {
        val dataset = ArrayList<ScheduleData>()
    }

    private var _binding: NavFragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NavFragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //툴바 타이틀 설정
        setTitle()
        setRecyclerView()
    }

    //툴바 타이틀 설정
    private fun setTitle() {
        val mMainactivity = activity as MainActivity
        mMainactivity.setTitle("홈")
    }

    fun setRecyclerView() {
        val mRecyclerView = binding.mainRecycler

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
        mRecyclerView.layoutManager = LinearLayoutManager(context)
    }

}
