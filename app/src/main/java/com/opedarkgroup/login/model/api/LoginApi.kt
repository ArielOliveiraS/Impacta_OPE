package com.opedarkgroup.login.model.api

import com.opedarkgroup.login.model.models.LoginResponse
import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("/Login/Login")
    fun getLogin(@Body loginResponse: LoginResponse) : Completable
}