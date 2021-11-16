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
//        private val BASE_URL = "http://192.168.162.78:"
        // AWS
        private val BASE_URL = "http://sroa-lb-106540279.ap-northeast-2.elb.amazonaws.com:"

//        private val Account_URL = "http://13.125.26.25:"
//        private val Repair_URL = "http://3.34.186.100:"
//        private val Inquery_URL = "http://3.34.182.42:"
//        private val Evaluation_URL = "http://3.36.88.190:"
        // 교육장
//        private val Account_URL = "http://192.168.162.78:"
//        private val Repair_URL = "http://192.168.162.78:"
//        private val Inquery_URL = "http://192.168.162.78:"
//        private val Evaluation_URL = "http://192.168.162.78:"
        // 집
//        private val Account_URL = "http://192.168.25.33:"
//        private val Repair_URL = "http://192.168.25.33:"
//        private val Inquery_URL = "http://192.168.25.33:"
//        private val Evaluation_URL = "http://192.168.25.33:"
        // 스터디카페
//        private val Account_URL = "http://172.30.1.42:"
//        private val Repair_URL = "http://172.30.1.42:"
//        private val Inquery_URL = "http://172.30.1.42:"
//        private val Evaluation_URL = "http://172.30.1.42:"

        private val signIn = "8081"
        private val repair = "8082"
        private val inquerySchedule = "8084"
        private val evaluation = "8085"
    }

    // 초기 로그인 레트로핏 인스턴스
    fun getSignInInstance(): GetSignInService {
        return retrofit2.Retrofit
            .Builder()
            .baseUrl( BASE_URL+ signIn)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetSignInService::class.java)
    }

    // 정보 로드 관련 레트로핏 인스턴스
    fun getData(): GetDataService {
        return retrofit2.Retrofit
            .Builder()
            .baseUrl(BASE_URL + inquerySchedule)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetDataService::class.java)
    }

    //엔지니어 일정 로드
    fun getRepairSchdule(): GetRepairSchduleService {
        return retrofit2.Retrofit
            .Builder()
            .baseUrl(BASE_URL + repair)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetRepairSchduleService::class.java)
    }

    fun getEvalutationInstance(): GetEvaluation {
        return retrofit2.Retrofit
            .Builder()
            .baseUrl(BASE_URL + evaluation)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetEvaluation::class.java)
    }
}