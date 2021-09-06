package com.clipcodetest.fragment.base

import android.os.Bundle
import androidx.activity.addCallback
import androidx.fragment.app.Fragment

open class BaseFragment: Fragment() {
    private lateinit var mContext: BaseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
    }

    fun disableOnBackPressed(){
        requireActivity().onBackPressedDispatcher.addCallback(this){}
    }

}