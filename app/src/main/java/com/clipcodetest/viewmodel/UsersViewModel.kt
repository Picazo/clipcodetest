package com.clipcodetest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clipcodetest.models.UserResponse
import com.clipcodetest.retrofit.UsersClient
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UsersViewModel(): ViewModel() {

    val userList = MutableLiveData<ArrayList<UserResponse>>()
    var userClient = UsersClient.getInstance()
    var arrayListUsers = ArrayList<UserResponse>()

    fun loadUser() {
        userClient.getUserList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .repeat(SIZE_USERS_LIST.toLong())
            .subscribe(object : Observer<UserResponse> {
                override fun onNext(user: UserResponse) {

                    arrayListUsers.add(user)
                }
                override fun onError(e: Throwable) {
                    //TODO: add liveData to hide progreess
                }
                override fun onComplete() {
                    userList.value = arrayListUsers
                }
                override fun onSubscribe(d: Disposable) {
                }
            })
    }

    companion object{
        val SIZE_USERS_LIST = 5
    }

}