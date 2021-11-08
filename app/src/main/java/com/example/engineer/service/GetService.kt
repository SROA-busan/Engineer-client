package com.example.engineer.service

import com.example.engineer.dto.EngineerInfo
import com.example.engineer.dto.ScheduleHandling
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
    @POST("/account/engineer/settingPw")
    fun engineerSettingPw(@Body engineerInfo: EngineerInfo): Call<Boolean>
}

// 엔지니어 관련 api
interface GetDataService{
    // id로 엔지니어 정보 로드
}

//엔지니어 일정 로드
interface GetRepairSchduleService{
    @POST("repair/engineer/requestForCompletion")
    fun requestForCompletion(@Body form: ScheduleHandling): Call<Boolean>

    @GET("/schedule/Engineer/MainPage/{id}")
    fun engineeerMainPage(@Path("id") id: String): Call<Any>
}