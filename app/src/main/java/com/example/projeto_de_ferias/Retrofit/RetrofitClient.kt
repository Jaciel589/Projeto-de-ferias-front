package com.example.projeto_de_ferias.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var instance : Retrofit?=null
    val getInstance: Retrofit
        get () {
            if(instance == null) {
                instance =  Retrofit.Builder()
                    .baseUrl("http://192.168.0.104:3000")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return instance!!
        }

}