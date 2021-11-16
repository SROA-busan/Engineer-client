package com.example.engineer.dto

import java.util.*

data class ResponseLoginEngineer(
    val centerName: String,
    val avgScore: Int,
    val name: String,
    val list: List<EngineerBrieflySchedule>
)

