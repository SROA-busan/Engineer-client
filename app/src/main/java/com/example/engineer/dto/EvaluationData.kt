package com.example.engineer.dto

import java.io.Serializable
import java.sql.Timestamp

class EvaluationData (
    val classifyName: String,
    val writeDate: Timestamp,
    val content: String,
    val score: Int
) : Serializable

