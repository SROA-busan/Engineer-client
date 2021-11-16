package com.example.engineer.dto

import java.io.Serializable
import java.sql.Timestamp

data class ResponseEngineerEvaluation(
    val classifyName: String,
    val writeDate: String,
    val content: String,
    val score: Int
) : Serializable

