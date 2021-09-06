package com.clipcodetest.data

import com.clipcodetest.models.UserResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface UsersApiService {

    @GET("api/")
    fun getUserInfo(
    ): Observable<UserResponse>

}
