package com.example.engineer.dto

import com.example.engineer.R
import java.io.Serializable

class EvaluationData (
    val dateTime: String = "0",
    val product: String = "1",
    val grade: String = "2",
    val content: String = "3"
) : Serializable