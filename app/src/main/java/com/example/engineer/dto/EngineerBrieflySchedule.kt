package com.example.engineer.dto

import java.io.Serializable

data class EngineerBrieflySchedule(
    val scheduleNum: Long,
    val startTime: String,
    val productName: String,
    val state: Int
):Serializable