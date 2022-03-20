package com.example.livedataexample.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private var integerA = ""

    private var integerB = ""

   // private var result: Float = 0f
    private  var result : MutableLiveData<Int> = MutableLiveData()


    fun setAmount(valueA: String, valueB: String) {

        this.integerA = valueA
        this.integerB = valueB

        //result = value.toFloat() * usd_to_eu_rate
    result.setValue(valueA.toInt()*valueB.toInt())
    }



    //fun getResult(): Float? {

    fun getResult():MutableLiveData<Int>{
        return result

    }
}