package com.example.engineer

import android.os.Bundle
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

class MainFragment : Fragment() {
    private var _binding: NavFragmentMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = NavFragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //툴바 타이틀 설정
        setTitle()
    }

    //툴바 타이틀 설정
    private fun setTitle(){
        val mMainactivity = activity as MainActivity
        mMainactivity.setTitle("홈")
    }
}