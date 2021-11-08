package com.example.engineer.network

import com.example.engineer.service.GetDataService
import com.example.engineer.service.GetSignInService
import retrofit2.converter.gson.GsonConverterFactory

// 레트로핏 인스턴스를 생성하는 클래스
class RetrofitInstance {
    companion object{
        private val BASE_URL = "http://192.168.25.33:"
        private val inquerySchedule = "8000"
        private val signIn = "8001"
        private val confirmEvaluation = "8002"
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
}