package com.example.engineer.dto

data class EngineerDetailSchedule(
    val scheduleNum: Long,
    val startTime: String,
    val endTime: String,
    val customerAddress: String,
    val customerPhoneNum: String,
    val productName: String,
    val state: Int,
    val content: String
)

