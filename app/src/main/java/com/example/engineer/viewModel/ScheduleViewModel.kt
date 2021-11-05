package com.example.engineer.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScheduleViewModel : ViewModel() {
    val dateTime = MutableLiveData<String>()
    val product = MutableLiveData<String>()
    val address = MutableLiveData<String>()
    val process = MutableLiveData<String>()
    val phoneNum = MutableLiveData<String>()
    val content = MutableLiveData<String>()
}