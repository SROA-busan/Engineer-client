package com.example.engineer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.engineer.databinding.MainActivityBinding
import com.example.engineer.schedule.ScheduleFragment
import com.example.engineer.confirm.ConfirmFragment
import com.example.engineer.setting.SettingFragment
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private var binding: MainActivityBinding? = null
    val view get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(view.root)

        if(Intent().hasExtra("pageName")) {
            val pageName = Intent().getStringExtra("pageName")!!
            setDetailNextPage(pageName)
        }

        setActionBar()
        replaceFragment(MainFragment())
        setNavFragment()

        }

    //툴바 설정
    private fun setActionBar(){
        val toolbar = view.mainToolBar
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar

        actionBar!!.setDisplayHomeAsUpEnabled(true) //왼쪽버튼 사용여부
        actionBar!!.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24) //왼쪽버튼 이미지 설정
    }

    //툴바 이름변경
    fun setTitle(title: String){
        view.mainToolBar.title = title
    }

    //툴바 메뉴버튼 설정
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_toolbar_menu, menu) //main_toolbar_menu를 toolbar메뉴버튼으로 설정
        return true
    }

    //툴바 메뉴 이벤트
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    // ScheduleDetailActivity에서 OK 눌렀을때 가는 프래그먼트
    private fun setDetailNextPage(page: String){
        when(page){
            "schF" -> replaceFragment(ScheduleFragment())
            "mainF" -> replaceFragment(MainFragment())
        }
    }

    //하단 네비
    private fun setNavFragment() {
        view.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                //홈
                R.id.home -> {
                    replaceFragment(MainFragment())
                    true
                }
                //일정
                R.id.schedule -> {
                    replaceFragment(ScheduleFragment())
                    true
                }
                // 평가조회
                R.id.confirm -> {
                    replaceFragment(ConfirmFragment())
                    true
                }
                //설정
                R.id.setting -> {
                    replaceFragment(SettingFragment())
                    true
                }
                else -> false
            }
        }
    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        //TODO replace를 쓰게되면 새로운 창을 여는 것 --> 이전상태가 저장이 안된다 --> 조회 -> 설정 -> 조회로 가면 조회가 초기화됨
        try{
            fragmentTransaction.replace(R.id.bottom_nav_frame, fragment)
            fragmentTransaction.commit()
        } catch (e : Exception){
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}