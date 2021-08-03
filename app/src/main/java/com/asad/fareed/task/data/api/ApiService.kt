package com.asad.fareed.task.data.api

import com.asad.fareed.task.data.model.User
import retrofit2.http.GET

interface ApiService {

    @GET("620V")
    suspend fun getUsers(): List<User>

}