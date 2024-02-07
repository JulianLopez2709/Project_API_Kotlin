package com.example.project_api_kotlin.data

import com.example.project_api_kotlin.domain.User.UserDTO
import retrofit2.Response
import retrofit2.http.GET

interface ReqresService {
    @GET("/api/users")
    suspend fun getAllUsers(): Response<UserDTO>

}