package com.clipcodetest.retrofit

import com.clipcodetest.data.UsersApiService
import com.clipcodetest.models.UserResponse
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class UsersClient {

    val TIME_OUT = 20

    private val usersService   =  Retrofit.Builder()
        .baseUrl("https://randomuser.me/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(getHttpClient())
        .build().create(UsersApiService::class.java)

    companion object{
        lateinit var INSTANCE: UsersClient
        fun getInstance(): UsersClient {
            INSTANCE = UsersClient()
            return INSTANCE
        }
    }

    fun getUserList():Observable<UserResponse>{
        return usersService.getUserInfo()
    }
    private fun getHttpClient(): OkHttpClient? {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient()
            .newBuilder()
            .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(Interceptor { chain ->
                val request: Request = chain.request()
                val newRequest: Request = request.newBuilder()
                    .build()
                chain.proceed(newRequest)
            })
            .build()
    }
}