package com.opedarkgroup.login.model.api

import com.opedarkgroup.login.model.models.LoginResponse
import retrofit2.http.GET

interface LoginApi {

    @GET("login/GetLogin?usuario=alan.esteves&senha=1234")
    suspend fun getLogin() : LoginResponse
}