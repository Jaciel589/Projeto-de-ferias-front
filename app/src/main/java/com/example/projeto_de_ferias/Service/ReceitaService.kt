package com.example.projeto_de_ferias.Service

import com.example.projeto_de_ferias.Models.Receita
import retrofit2.Call
import retrofit2.http.*

interface ReceitaService {

    @GET("/receitas/{user_id}")
    fun getReceitas(@Path("user_id")id: String): Call<ArrayList<Receita>>

    @POST("/receitas")
    fun postReceita(@Body receita: Receita): Call<Receita>

    @PUT("/receitas/{id}")
    fun editarReceita(@Path("id")id: String, @Body receita : Receita) : Call<Receita>

    @DELETE("/receitas/{id}")
    fun excluirReceita(@Path("id") id: String) : Call<Receita>
}