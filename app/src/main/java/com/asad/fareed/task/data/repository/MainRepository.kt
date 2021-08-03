package com.asad.fareed.task.data.repository

import com.asad.fareed.task.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()
}