package com.example.cinesharp_mobile.servicios

import com.example.cinesharp_mobile.dtos.CredencialesLoginDTO
import com.example.cinesharp_mobile.dtos.RespuestaAuthDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login")
    suspend fun login(@Body dto: CredencialesLoginDTO): RespuestaAuthDTO

}