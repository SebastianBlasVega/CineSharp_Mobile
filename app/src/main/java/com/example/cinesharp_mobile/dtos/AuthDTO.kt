package com.example.cinesharp_mobile.dtos

import com.google.gson.annotations.SerializedName

data class CredencialesLoginDTO(
    val email: String,
    val contrasena: String
)

data class RespuestaAuthDTO(
    val token: TokenInternoDTO
)

data class TokenInternoDTO(
    val token: String?,
    val mensaje: String
)
