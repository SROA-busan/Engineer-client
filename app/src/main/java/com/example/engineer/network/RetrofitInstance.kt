package com.example.engineer.network

import com.example.engineer.service.GetDataService
import com.example.engineer.service.GetEvaluation
import com.example.engineer.service.GetRepairSchduleService
import com.example.engineer.service.GetSignInService
import com.google.gson.Gson
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

// 레트로핏 인스턴스를 생성하는 클래스
class RetrofitInstance {
    companion object{
        private val BASE_URL = "http://192.168.0.4:"
        private val inquerySchedule = "8000"
        private val signIn = "8001"
        private val Evaluation = "8002"
        private val REPAIR = "8003"
    }

    // 초기 로그인 레트로핏 인스턴스
    fun getSignInInstance(): GetSignInService{
        return retrofit2.Retrofit
            .Builder()
            .baseUrl(BASE_URL+signIn)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetSignInService::class.java)
    }

    // 정보 로드 관련 레트로핏 인스턴스
    fun getData(): GetDataService{
        return retrofit2.Retrofit
            .Builder()
            .baseUrl(BASE_URL+ inquerySchedule)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetDataService::class.java)
    }

    //엔지니어 일정 로드
    fun getRepairSchdule(): GetRepairSchduleService{
        return retrofit2.Retrofit
            .Builder()
            .baseUrl(BASE_URL+ REPAIR)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetRepairSchduleService::class.java)
    }

    fun getEvalutationInstance(): GetEvaluation{
        return retrofit2.Retrofit
            .Builder()
            .baseUrl(BASE_URL+ Evaluation)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetEvaluation::class.java)
    }
}