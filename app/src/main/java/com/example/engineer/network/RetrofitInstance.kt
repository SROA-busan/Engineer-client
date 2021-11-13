package com.example.engineer.network

import com.example.engineer.service.GetDataService
import com.example.engineer.service.GetEvaluation
import com.example.engineer.service.GetRepairSchduleService
import com.example.engineer.service.GetSignInService
import retrofit2.converter.gson.GsonConverterFactory

// 레트로핏 인스턴스를 생성하는 클래스
class RetrofitInstance {
    companion object {
//        private val BASE_URL = "http://192.168.162.242:"
//        private val BASE_URL = "http://192.168.25.33:"
//        private val BASE_URL = "http://192.168.162.78:"
        // AWS
//        private val Account_URL = "http://3.36.130.67:"
//        private val Repair_URL = "http://52.79.203.71:"
//        private val Inquery_URL = "http://3.34.140.158:"
//        private val Evaluation_URL = "http://52.78.98.197:"
        // 교육장
//        private val Account_URL = "http://192.168.162.78:"
//        private val Repair_URL = "http://192.168.162.78:"
//        private val Inquery_URL = "http://192.168.162.78:"
//        private val Evaluation_URL = "http://192.168.162.78:"
        // 집
        private val Account_URL = "http://192.168.25.33:"
        private val Repair_URL = "http://192.168.25.33:"
        private val Inquery_URL = "http://192.168.25.33:"
        private val Evaluation_URL = "http://192.168.25.33:"

        private val signIn = "8081"
        private val repair = "8082"
        private val inquerySchedule = "8084"
        private val evaluation = "8085"
    }

    // 초기 로그인 레트로핏 인스턴스
    fun getSignInInstance(): GetSignInService {
        return retrofit2.Retrofit
            .Builder()
            .baseUrl( Account_URL+ signIn)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetSignInService::class.java)
    }

    // 정보 로드 관련 레트로핏 인스턴스
    fun getData(): GetDataService {
        return retrofit2.Retrofit
            .Builder()
            .baseUrl(Inquery_URL + inquerySchedule)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetDataService::class.java)
    }

    //엔지니어 일정 로드
    fun getRepairSchdule(): GetRepairSchduleService {
        return retrofit2.Retrofit
            .Builder()
            .baseUrl(Repair_URL + repair)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetRepairSchduleService::class.java)
    }

    fun getEvalutationInstance(): GetEvaluation {
        return retrofit2.Retrofit
            .Builder()
            .baseUrl(Evaluation_URL + evaluation)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetEvaluation::class.java)
    }
}