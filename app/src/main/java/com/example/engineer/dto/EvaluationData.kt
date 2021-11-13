package com.example.engineer.dto

import java.io.Serializable
import java.sql.Timestamp

class EvaluationData (
    val evaluationNum: Long,
    val writeDate: String,
    val content: String,
    val score: Int
) : Serializable

