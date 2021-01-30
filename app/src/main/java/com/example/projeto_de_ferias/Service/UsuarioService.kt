package com.example.projeto_de_ferias.Service

import com.example.projeto_de_ferias.Models.Auth
import com.example.projeto_de_ferias.Models.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UsuarioService {

    @POST("/users")
    fun postUsuario(@Body usuario: Usuario): Call<Usuario>

    @POST("/sessions")
    fun loginUsuario(@Body auth: Auth): Call<Auth>
}