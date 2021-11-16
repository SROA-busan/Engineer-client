package com.example.engineer.service

import com.example.engineer.dto.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

// api경로 인터페이스
// 유저 로그인, 초기 로그인 api
interface GetSignInService{
    // 로그인
    @GET("/account/login/{ID}/{PW}")
    fun login(@Path("ID") id: String, @Path("PW") pw: String): Call<Int>
    // 초기 비밀번호 변경
    @GET("/account/settingPw/{ID}/{PW}")
    fun engineerSettingPw(@Path("ID") id: String, @Path("PW") pw: String): Call<Boolean>
}

// 엔지니어 관련 api
interface GetDataService{
    //입고 현황 조회
    @GET("/schedule/Engineer/InqueryWarehousingProduct/{id}")
    fun getWarehousingProduct(@Path("id") id: String): Call<List<ResponseWorkOfdateEngineer>>

    //메인 페이지 일정 조회
    @GET("/schedule/Engineer/MainPage/{id}")
    fun engineeerMainPage(@Path("id") id: String): Call<ResponseLoginEngineer>

    //일정 상세 조회
    @GET("/schedule/Engineer/SelectOneSchedule/{scheduleNum}")
    fun selectOneSchedule(@Path("scheduleNum")scheduleNum: Long): Call<EngineerDetailSchedule>

    //월간 일정 조회
    @GET("/schedule/Engineer/InqueryWorkOfMonth/{id}/{month}")
    fun inqueryWorkCntOfMonth(@Path("id")id: String, @Path("month")month: String): Call<List<ResponseWorkCntOfMonthEngineer>>

    //일간 일정 조회 -> 달력에서 날짜를 클릭했을 때
    @GET("/schedule/Engineer/InqueryWorkOfDate/{id}/{date}")
    fun inqueryWorkOfDate(@Path("id")id: String, @Path("date")date: String): Call<List<ResponseWorkOfdateEngineer>>
    
}
//평가조회
interface GetEvaluation {
    //평가 조회
    @GET("/evaluation/engineer/inqueryEvaluation/{id}")
    fun inqueryEvalution(@Path("id") id: String): Call<List<ResponseEngineerEvaluation>>
}

// 스케줄 상태
interface GetRepairSchduleService{
    // 입고 처리
    @GET("repair/engineer/requestWarehousing/{scheduleNum}")
    fun requestWarehousing(@Path("scheduleNum") schduleNum:Long):Call<Boolean>

    // 고객대면, 처리완료
    @GET("repair/engineer/requestComplete/{scheduleNum}")
    fun requestComplete(@Path("scheduleNum") schduleNum:Long):Call<Boolean>

    // 입고중인 장비 수리 완료
    @GET("repair/engineer/requestRepair/{scheduleNum}")
    fun requestRepair(@Path("scheduleNum") schduleNum:Long):Call<Boolean>
}

