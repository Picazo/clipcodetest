package com.clipcodetest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.clipcodetest.R
import com.clipcodetest.utils.Loader

class MainActivity : AppCompatActivity() {

    lateinit var loader: Loader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initLoader()
    }

    private fun initLoader(){
        loader = Loader(this, supportFragmentManager)
    }

}