package com.nayanthayasiru.simple_calculator.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var result = MutableLiveData<Double>(0.0)
    fun add(num1: Double, num2: Double){
        result.value = num1.plus(num2)
    }
}