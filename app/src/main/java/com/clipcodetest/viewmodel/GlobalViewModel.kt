package com.clipcodetest.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GlobalViewModel(): ViewModel() {

    var splashMd: MutableLiveData<Boolean> = MutableLiveData()
    private lateinit var timer: CountDownTimer

    fun startCountDown(){
        timer = object : CountDownTimer(2000,500){
            override fun onFinish() {
                splashMd.value = true
            }
            override fun onTick(millisUntilFinished: Long) {
            }
        }.start()
    }

}